#!/usr/bin/env python3
import sys
from pathlib import Path

class CompileRefs:
    def __init__(self):
        # directory containing this script: .../language/fieldRef
        self.script_dir = Path(__file__).resolve().parent
        # language root: .../language
        self.language_root = self.script_dir.parent
        # absolute paths to field and fieldRef directories under language/
        self.field_root = (self.language_root / "field").resolve()
        self.ref_root = (self.language_root / "fieldRef").resolve()

    def run(self):
        # ensure fieldRef root exists
        self.ref_root.mkdir(parents=True, exist_ok=True)
        # if field root doesn't exist nothing to do
        if not self.field_root.exists():
            print(f"Field directory not found: {self.field_root}", file=sys.stderr)
            return
        self.explore(self.field_root)
        self.cleanup(self.ref_root)

    def explore(self, current_field_dir: Path):
        """Recursively explore the given field directory and generate corresponding ref classes."""
        # compute the mirror directory under fieldRef
        rel = current_field_dir.relative_to(self.field_root)
        current_ref_dir = self.ref_root / rel

        current_ref_dir.mkdir(parents=True, exist_ok=True)

        try:
            entries = list(current_field_dir.iterdir())
        except (FileNotFoundError, NotADirectoryError):
            return

        for entry in entries:
            if entry.is_dir():
                self.explore(entry)
            elif entry.is_file() and entry.suffix == ".java":
                self.write_ref(entry)

    def cleanup(self, current_ref_dir: Path):
        """Recursively remove ref files that no longer have corresponding field files, and remove empty dirs."""
        if not current_ref_dir.exists():
            return

        try:
            entries = list(current_ref_dir.iterdir())
        except (FileNotFoundError, NotADirectoryError):
            return

        for entry in entries:
            if entry.is_dir():
                self.cleanup(entry)
            elif entry.is_file() and entry.name not in ("CompileRefs.py", "Ref.java"):
                # map ref file to candidate field file
                rel = entry.relative_to(self.ref_root)
                parent_rel = rel.parent  # subpath after 'fieldRef'
                stem = entry.stem  # e.g. BoolFieldRef
                if stem.endswith("Ref"):
                    base_name = stem[:-3] + ".java"
                else:
                    base_name = stem + ".java"
                field_candidate = self.field_root / parent_rel / base_name
                if not field_candidate.exists():
                    try:
                        entry.unlink()
                    except OSError:
                        pass

        # try to remove the directory if it's empty now
        try:
            current_ref_dir.rmdir()
        except OSError:
            # not empty or failed; ignore
            pass

    def write_ref(self, file: Path):
        file_path = file.resolve()

        # compute relative path under field root
        try:
            rel_field_path = file_path.relative_to(self.field_root)
        except Exception as e:
            print(f"Skipping file not under field root: {file_path} ({e})", file=sys.stderr)
            return

        field_class_name = rel_field_path.stem
        ref_class_name = field_class_name + "Ref"

        ref_file_path = self.ref_root / rel_field_path.parent / (ref_class_name + ".java")
        pkg_name = self.package_from_path(ref_file_path)

        field_file_path = self.field_root / rel_field_path
        import_name = self.package_from_path(field_file_path) + "." + field_class_name

        content = f"""package {pkg_name};

import language.fieldRef.Ref;

import {import_name};

/** Represents a reference to a {field_class_name} value */
public class {ref_class_name} extends Ref<{field_class_name}> {{
    private {field_class_name} field;
    
    public {ref_class_name}(){{}}
    public {ref_class_name}({field_class_name} field){{
        this();
        this.field = field;
    }}

    /** Sets the referenced value. */
    @Override
    public void set({field_class_name} value) {{ field = value; }}

    /** @return the referenced value. */
    @Override
    public {field_class_name} get() {{ return field; }}

/** @return a reference to a copy of the referenced value. */
    @Override
    public {ref_class_name} copy() {{ 
        if (field != null) return new {ref_class_name}(field.copy());
        return new {ref_class_name}();
    }}
}}
"""

        try:
            ref_file_path.parent.mkdir(parents=True, exist_ok=True)
            ref_file_path.write_text(content, encoding='utf-8')
        except IOError as e:
            print(f"Failed to write reference class for {field_class_name}: {e}", file=sys.stderr)

    def package_from_path(self, file_path: Path) -> str:
        """Return a package string like 'language.fieldRef.boolField' for the given file path."""
        abs_path = Path(file_path).resolve()
        try:
            rel = abs_path.parent.relative_to(self.language_root)
        except Exception:
            # fallback: use path parts after the last 'language' component
            parts = abs_path.parent.parts
            if "language" in parts:
                idx = len(parts) - parts[::-1].index("language") - 1
                rel = Path(*parts[idx + 1 :])
            else:
                rel = Path()
        if rel.parts:
            return "language." + ".".join(rel.parts)
        return "language"

def main():
    CompileRefs().run()

if __name__ == "__main__":
    main()