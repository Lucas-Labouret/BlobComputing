package prog;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CompileRefs {
    private static final Path SRC_ROOT = Paths.get("src", "main", "java").toAbsolutePath().normalize();

    /** Mirrors the file structure of .../obj/ into .../ref/, and automatically writes (or delete) the reference classes. */
    static void main(String[] args) {
        new CompileRefs().run();
    }

    private void run() {
        String objPath = "src/main/java/prog/obj/";
        String refPath = "src/main/java/prog/ref/";

        File objDir = new File(objPath);
        File refDir = new File(refPath);

        if (!objDir.exists()) { boolean _ = objDir.mkdirs(); }
        if (!refDir.exists()) { boolean _ = refDir.mkdirs(); }

        explore(objPath);
        cleanup(refPath);
    }

    /**
     * Recursively explores the given directory, creating reference classes and mirroring new directories.
     * @param currentObjPath the current object path
     */
    private void explore(String currentObjPath) {
        String currentRefPath = currentObjPath.replaceFirst("obj", "ref");
        File currentObjDir = new File(currentObjPath);
        File currentRefDir = new File(currentRefPath);

        if (!currentRefDir.exists()) { boolean _ = currentRefDir.mkdirs(); }

        File[] files = currentObjDir.listFiles();
        if (files == null) return;

        for (File file: files) {
            if (file.isDirectory()) explore(file.getPath());
            else writeRef(file);
        }
    }

    /**
     * Recursively explore the given directory, deleting any reference classes and directories the no longer have a corresponding object class or directory.
     * @param currentRefPath the current ref path
     */
    private void cleanup(String currentRefPath) {
        String currentObjPath = currentRefPath.replaceFirst("ref", "obj");
        File currentObjDir = new File(currentObjPath);
        File currentRefDir = new File(currentRefPath);

        if (!currentObjDir.exists()) { boolean _ = currentRefDir.delete(); }

        File[] files = currentRefDir.listFiles();
        if (files == null) return;

        for (File file: files) {
            if (file.isDirectory()) cleanup(file.getPath());
            else {
                String objFilePath = file.getPath().replaceFirst("ref", "obj").replaceFirst("Ref\\.java$", ".java");
                if (!new File(objFilePath).exists()) { boolean _ = file.delete(); }
            }
        }
    }

    private void writeRef(File file){
        Path objPath = file.toPath().toAbsolutePath().normalize();
        Path objRoot = Paths.get("src", "main", "java", "prog", "obj").toAbsolutePath().normalize();
        Path refRoot = Paths.get("src", "main", "java", "prog", "ref").toAbsolutePath().normalize();

        Path relObjPath = objRoot.relativize(objPath);
        String objClassName = relObjPath.getFileName().toString().replaceFirst("\\.java$", "");
        String refClassName = objClassName + "Ref";

        Path refFilePath = refRoot.resolve(relObjPath).resolveSibling(refClassName + ".java");
        String pkgName = packageFromPath(refFilePath);

        Path objFilePath = objRoot.resolve(relObjPath).resolveSibling(objClassName + ".java");
        String importName = packageFromPath(objFilePath) + "." + objClassName;

        String content =
                "package " + pkgName + ";\n" +
                "\n" +
                "import language.Ref;\n" +
                "\n" +
                "import " + importName + ";\n" +
                "\n" +
                "/** Represents a reference to a " + objClassName + " value */\n" +
                "public class " + refClassName + " extends Ref<" + objClassName + "> {\n" +
                "    private " + objClassName + " field;\n" +
                "\n" +
                "    /** Sets the referenced value. */\n" +
                "    @Override\n" +
                "    public void set(" + objClassName + " value) { field = value; }\n" +
                "\n" +
                "    /** @return the referenced value. */\n" +
                "    @Override\n" +
                "    public " + objClassName + " get() { return field; }\n" +
                "\n" +
                "    /** @return a reference to the given " + objClassName + ". */\n" +
                "    public static " + refClassName + " of(" + objClassName + " value) {\n" +
                "        " + refClassName + " ref = new " + refClassName + "();\n" +
                "        ref.field = value;\n" +
                "        return ref;\n" +
                "    }\n" +
                "\n" +
                "/** @return a reference to a copy of the referenced value. */\n" +
                "    @Override\n" +
                "    public " + refClassName + " copy() { return " + refClassName + ".of(field.copy()); }\n" +
                "}\n";

        try {
            Files.writeString(refFilePath, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Failed to write reference class for " + objClassName + ": " + e.getMessage());
        }
    }

    private static String packageFromPath(Path filePath) {
        Path abs = filePath.toAbsolutePath().normalize();
        Path relToSrc = SRC_ROOT.relativize(abs.getParent());
        return relToSrc.toString().replace('\\', '.').replace('/', '.');
    }
}