import os
import sys
from pathlib import Path

class CompileRefs:
    def __init__(self):
        self.src_root = Path("/").resolve()

    def run(self):
        obj_path = "obj/"
        ref_path = "ref/"

        obj_dir = Path(obj_path)
        ref_dir = Path(ref_path)

        obj_dir.mkdir(parents=True, exist_ok=True)
        ref_dir.mkdir(parents=True, exist_ok=True)

        self.explore(obj_path)
        self.cleanup(ref_path)

    def explore(self, current_obj_path):
        """Recursively explores the given directory, creating reference classes and mirroring new directories."""
        current_ref_path = current_obj_path.replace("obj", "ref")
        current_obj_dir = Path(current_obj_path)
        current_ref_dir = Path(current_ref_path)

        current_ref_dir.mkdir(parents=True, exist_ok=True)

        try:
            files = list(current_obj_dir.iterdir())
        except (FileNotFoundError, NotADirectoryError):
            return

        for file in files:
            if file.is_dir():
                self.explore(str(file))
            else:
                self.write_ref(file)

    def cleanup(self, current_ref_path):
        """Recursively explore the given directory, deleting any reference classes and directories that no longer have a corresponding object class or directory."""
        current_obj_path = current_ref_path.replace("ref", "obj")
        current_obj_dir = Path(current_obj_path)
        current_ref_dir = Path(current_ref_path)

        if not current_obj_dir.exists():
            try:
                current_ref_dir.rmdir()
            except OSError:
                pass
            return

        try:
            files = list(current_ref_dir.iterdir())
        except (FileNotFoundError, NotADirectoryError):
            return

        for file in files:
            if file.is_dir():
                self.cleanup(str(file))
            else:
                obj_file_path = str(file).replace("ref", "obj").replace("Ref.java", ".java")
                if not Path(obj_file_path).exists():
                    try:
                        file.unlink()
                    except OSError:
                        pass

    def write_ref(self, file):
        file_path = file.resolve()
        obj_root = Path("obj/").resolve()
        ref_root = Path("ref/").resolve()

        rel_obj_path = file_path.relative_to(obj_root)
        obj_class_name = rel_obj_path.stem
        ref_class_name = obj_class_name + "Ref"

        ref_file_path = ref_root / rel_obj_path.parent / (ref_class_name + ".java")
        pkg_name = self.package_from_path(ref_file_path)

        obj_file_path =  obj_root / rel_obj_path
        import_name = self.package_from_path(obj_file_path) + "." + obj_class_name

        content = f"""package {pkg_name};

import language.Ref;

import {import_name};

/** Represents a reference to a {obj_class_name} value */
public class {ref_class_name} extends Ref<{obj_class_name}> {{
    private {obj_class_name} field;

    /** Sets the referenced value. */
    @Override
    public void set({obj_class_name} value) {{ field = value; }}

    /** @return the referenced value. */
    @Override
    public {obj_class_name} get() {{ return field; }}

    /** @return a reference to the given {obj_class_name}. */
    public static {ref_class_name} of({obj_class_name} value) {{
        {ref_class_name} ref = new {ref_class_name}();
        ref.field = value;
        return ref;
    }}

/** @return a reference to a copy of the referenced value. */
    @Override
    public {ref_class_name} copy() {{ 
        if (field != null) return {ref_class_name}.of(field.copy());
        return new {ref_class_name}();
    }}
}}
"""

        try:
            ref_file_path.parent.mkdir(parents=True, exist_ok=True)
            ref_file_path.write_text(content, encoding='utf-8')
        except IOError as e:
            print(f"Failed to write reference class for {obj_class_name}: {e}", file=sys.stderr)

    @staticmethod
    def package_from_path(file_path):
        abs_path = file_path.resolve()
        src_root = Path("").resolve()
        rel_to_src = abs_path.parent.relative_to(src_root)
        return "language." + str(rel_to_src).replace('\\', '.').replace('/', '.')


def main():
    CompileRefs().run()


if __name__ == "__main__":
    main()