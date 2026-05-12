package medium;

import medium.locusS.Edge;
import medium.locusS.Face;
import medium.locusS.Vertex;
import medium.locusT.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Medium {
    public final double height, width;

    public final HashSet<Vertex> vertices;
    public final HashSet<Edge> edges;
    public final HashSet<Face> faces;
    public final HashSet<Ve> ves;
    public final HashSet<Vf> vfs;
    public final HashSet<Ev> evs;
    public final HashSet<Ef> efs;
    public final HashSet<Fv> fvs;
    public final HashSet<Fe> fes;

    public Medium(
            Collection<Vertex> vertices,
            Collection<Edge> edges,
            Collection<Face> faces,
            Collection<Ve> ves,
            Collection<Vf> vfs,
            Collection<Ev> evs,
            Collection<Ef> efs,
            Collection<Fv> fvs,
            Collection<Fe> fes
    ) {
        this(0, 0, vertices, edges, faces, ves, vfs, evs, efs, fvs, fes);
    }

    public Medium(
            double height, double width,
            Collection<Vertex> vertices,
            Collection<Edge> edges,
            Collection<Face> faces,
            Collection<Ve> ves,
            Collection<Vf> vfs,
            Collection<Ev> evs,
            Collection<Ef> efs,
            Collection<Fv> fvs,
            Collection<Fe> fes
    ) {
        this.height = height;
        this.width = width;
        this.vertices = new HashSet<>(vertices);
        this.edges = new HashSet<>(edges);
        this.faces = new HashSet<>(faces);
        this.ves = new HashSet<>(ves);
        this.vfs = new HashSet<>(vfs);
        this.evs = new HashSet<>(evs);
        this.efs = new HashSet<>(efs);
        this.fvs = new HashSet<>(fvs);
        this.fes = new HashSet<>(fes);
    }
    private static final String DEFAULT_LOCATION = "medium/";
    private static final String DEFAULT_EXTENSION = ".can";

    public static Medium read(String fileName) throws IOException {
        String fullName = DEFAULT_LOCATION + fileName + DEFAULT_EXTENSION;
        BufferedReader reader = new BufferedReader(new FileReader(fullName));

        HashSet<Vertex> vertices = new HashSet<>();
        HashSet<Edge> edges = new HashSet<>();
        HashSet<Face> faces = new HashSet<>();
        HashSet<Ve> ves = new HashSet<>();
        HashSet<Vf> vfs = new HashSet<>();
        HashSet<Ev> evs = new HashSet<>();
        HashSet<Ef> efs = new HashSet<>();
        HashSet<Fv> fvs = new HashSet<>();
        HashSet<Fe> fes = new HashSet<>();

        ArrayList<Ve> indexToVe = new ArrayList<>();
        ArrayList<Vf> indexToVf = new ArrayList<>();
        ArrayList<Ev> indexToEv = new ArrayList<>();
        ArrayList<Ef> indexToEf = new ArrayList<>();
        ArrayList<Fv> indexToFv = new ArrayList<>();
        ArrayList<Fe> indexToFe = new ArrayList<>();

        int lineCount = 0;

        String line = reader.readLine();
        if (line == null) throw new IOException("Empty file");
        if (!line.equals("-- Dimensions --"))
            throw new IOException("Expected dimensions section at line " + lineCount);

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after dimensions header");
        String[] dimStr = line.split(" ");
        if (dimStr.length != 2)
            throw new IOException("Expected two dimensions at line " + lineCount);
        double height, width;
        try {
            height = Double.parseDouble(dimStr[0]);
            width = Double.parseDouble(dimStr[1]);
        } catch (NumberFormatException e) {
            throw new IOException("Invalid dimensions format at line " + lineCount, e);
        }

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after dimensions");
        if (!line.isEmpty())
            throw new IOException("Expected empty line after dimensions at line " + lineCount);

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after dimensions section");
        if (!line.equals("-- Vertices --"))
            throw new IOException("Expected Vertices section header at line " + lineCount);

        while (!(line = reader.readLine()).isEmpty()){
            lineCount++;

            String[] vStr = line.split(" ");
            if (vStr.length != 6)
                throw new IOException("Expected 6 values for vertex at line " + lineCount);
            try {
                double h = Double.parseDouble(vStr[1]);
                double w = Double.parseDouble(vStr[2]);
                int y = Integer.parseInt(vStr[3]);
                int x = Integer.parseInt(vStr[4]);
                String[] borders = vStr[5].split("");
                if (borders.length != 4)
                    throw new IOException("Expected 4 border values for vertex at line " + lineCount);
                vertices.add(new Vertex(h, w, y, x, borders[0].equals("T"), borders[1].equals("T"), borders[2].equals("T"), borders[3].equals("T")));
            } catch (NumberFormatException e) {
                throw new IOException("Invalid vertex format at line " + lineCount, e);
            }
        }
        lineCount++;

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after vertices section");
        if (!line.equals("-- Ve --"))
            throw new IOException("Expected Ve section header at line " + lineCount);

        while (!(line = reader.readLine()).isEmpty()){
            lineCount++;

            String[] veStr = line.split(" ");
            if (veStr.length != 6)
                throw new IOException("Expected 6 values for Ve at line " + lineCount);
            try {
                double h = Double.parseDouble(veStr[1]);
                double w = Double.parseDouble(veStr[2]);
                int y = Integer.parseInt(veStr[3]);
                int x = Integer.parseInt(veStr[4]);
                int t = Integer.parseInt(veStr[5]);
                Ve ve = new Ve(h, w, y, x, t);
                ves.add(ve);
                indexToVe.add(ve);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid Ve format at line " + lineCount, e);
            }
        }
        lineCount++;

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after vertices section");
        if (!line.equals("-- Vf --"))
            throw new IOException("Expected Vf section header at line " + lineCount);

        while (!(line = reader.readLine()).isEmpty()){
            lineCount++;

            String[] vfStr = line.split(" ");
            if (vfStr.length != 6)
                throw new IOException("Expected 6 values for Vf at line " + lineCount);
            try {
                double h = Double.parseDouble(vfStr[1]);
                double w = Double.parseDouble(vfStr[2]);
                int y = Integer.parseInt(vfStr[3]);
                int x = Integer.parseInt(vfStr[4]);
                int t = Integer.parseInt(vfStr[5]);
                Vf vf = new Vf(h, w, y, x, t);
                vfs.add(vf);
                indexToVf.add(vf);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid Vf format at line " + lineCount, e);
            }
        }
        lineCount++;

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after vertices section");
        if (!line.equals("-- Edges --"))
            throw new IOException("Expected Edges section header at line " + lineCount);

        while (!(line = reader.readLine()).isEmpty()){
            lineCount++;

            String[] eStr = line.split(" ");
            if (eStr.length != 7)
                throw new IOException("Expected 7 values for Edge at line " + lineCount);
            try {
                double h = Double.parseDouble(eStr[1]);
                double w = Double.parseDouble(eStr[2]);
                int y = Integer.parseInt(eStr[3]);
                int x = Integer.parseInt(eStr[4]);
                int t = Integer.parseInt(eStr[5]);
                String[] borders = eStr[6].split("");
                if (borders.length != 4)
                    throw new IOException("Expected 4 border values for vertex at line " + lineCount);
                edges.add(new Edge(h, w, y, x, t, borders[0].equals("T"), borders[1].equals("T"), borders[2].equals("T"), borders[3].equals("T")));
            } catch (NumberFormatException e) {
                throw new IOException("Invalid Edge format at line " + lineCount, e);
            }
        }
        lineCount++;

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after vertices section");
        if (!line.equals("-- Ev --"))
            throw new IOException("Expected Ev section header at line " + lineCount);

        while (!(line = reader.readLine()).isEmpty()){
            lineCount++;

            String[] evStr = line.split(" ");
            if (evStr.length != 7)
                throw new IOException("Expected 6 values for Ev at line " + lineCount);
            try {
                double h = Double.parseDouble(evStr[1]);
                double w = Double.parseDouble(evStr[2]);
                int y = Integer.parseInt(evStr[3]);
                int x = Integer.parseInt(evStr[4]);
                int t = Integer.parseInt(evStr[5]);
                int s = Integer.parseInt(evStr[6]);
                Ev ev = new Ev(h, w, y, x, t, s);
                evs.add(ev);
                indexToEv.add(ev);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid Ev format at line " + lineCount, e);
            }
        }
        lineCount++;

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after vertices section");
        if (!line.equals("-- Ef --"))
            throw new IOException("Expected Ef section header at line " + lineCount);

        while (!(line = reader.readLine()).isEmpty()){
            lineCount++;

            String[] efStr = line.split(" ");
            if (efStr.length != 7)
                throw new IOException("Expected 6 values for Ef at line " + lineCount);
            try {
                double h = Double.parseDouble(efStr[1]);
                double w = Double.parseDouble(efStr[2]);
                int y = Integer.parseInt(efStr[3]);
                int x = Integer.parseInt(efStr[4]);
                int t = Integer.parseInt(efStr[5]);
                int s = Integer.parseInt(efStr[6]);
                Ef ef = new Ef(h, w, y, x, t, s);
                efs.add(ef);
                indexToEf.add(ef);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid Ef format at line " + lineCount, e);
            }
        }
        lineCount++;

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after vertices section");
        if (!line.equals("-- Faces --"))
            throw new IOException("Expected Faces section header at line " + lineCount);

        while (!(line = reader.readLine()).isEmpty()){
            lineCount++;

            String[] fStr = line.split(" ");
            if (fStr.length != 6)
                throw new IOException("Expected 6 values for Face at line " + lineCount);
            try {
                double h = Double.parseDouble(fStr[1]);
                double w = Double.parseDouble(fStr[2]);
                int y = Integer.parseInt(fStr[3]);
                int x = Integer.parseInt(fStr[4]);
                int t = Integer.parseInt(fStr[5]);
                faces.add(new Face(h, w, y, x, t));
            } catch (NumberFormatException e) {
                throw new IOException("Invalid Face format at line " + lineCount, e);
            }
        }
        lineCount++;

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after vertices section");
        if (!line.equals("-- Fv --"))
            throw new IOException("Expected Fv section header at line " + lineCount);

        while (!(line = reader.readLine()).isEmpty()){
            lineCount++;

            String[] fvStr = line.split(" ");
            if (fvStr.length != 7)
                throw new IOException("Expected 6 values for Fv at line " + lineCount);
            try {
                double h = Double.parseDouble(fvStr[1]);
                double w = Double.parseDouble(fvStr[2]);
                int y = Integer.parseInt(fvStr[3]);
                int x = Integer.parseInt(fvStr[4]);
                int t = Integer.parseInt(fvStr[5]);
                int s = Integer.parseInt(fvStr[6]);
                Fv fv = new Fv(h, w, y, x, t, s);
                fvs.add(fv);
                indexToFv.add(fv);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid Fv format at line " + lineCount, e);
            }
        }
        lineCount++;

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after vertices section");
        if (!line.equals("-- Fe --"))
            throw new IOException("Expected Fe section header at line " + lineCount);

        while (!(line = reader.readLine()).isEmpty()){
            lineCount++;

            String[] feStr = line.split(" ");
            if (feStr.length != 7)
                throw new IOException("Expected 6 values for Fe at line " + lineCount);
            try {
                double h = Double.parseDouble(feStr[1]);
                double w = Double.parseDouble(feStr[2]);
                int y = Integer.parseInt(feStr[3]);
                int x = Integer.parseInt(feStr[4]);
                int t = Integer.parseInt(feStr[5]);
                int s = Integer.parseInt(feStr[6]);
                Fe fe = new Fe(h, w, y, x, t, s);
                fes.add(fe);
                indexToFe.add(fe);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid Fe format at line " + lineCount, e);
            }
        }
        lineCount++;

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after vertices section");
        if (!line.equals("-- Ve <-> Ev --"))
            throw new IOException("Expected Ve <-> Ev section header at line " + lineCount);

        while (!(line = reader.readLine()).isEmpty()){
            lineCount++;

            String[] veevStr = line.split(" ");
            if (veevStr.length != 2)
                throw new IOException("Expected 2 values for Ve <-> Ev at line " + lineCount);
            try {
                int veIndex = Integer.parseInt(veevStr[0]);
                int evIndex = Integer.parseInt(veevStr[1]);
                Ve ve = indexToVe.get(veIndex);
                Ev ev = indexToEv.get(evIndex);
                ve.pairWith(ev);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid Ve <-> Ev format at line " + lineCount, e);
            }
        }
        lineCount++;

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after vertices section");
        if (!line.equals("-- Vf <-> Fv --"))
            throw new IOException("Expected Vf <-> Fv section header at line " + lineCount);

        while (!(line = reader.readLine()).isEmpty()){
            lineCount++;

            String[] vffvStr = line.split(" ");
            if (vffvStr.length != 2)
                throw new IOException("Expected 2 values for Vf <-> Fv at line " + lineCount);
            try {
                int vfIndex = Integer.parseInt(vffvStr[0]);
                int fvIndex = Integer.parseInt(vffvStr[1]);
                Vf vf = indexToVf.get(vfIndex);
                Fv fv = indexToFv.get(fvIndex);
                vf.pairWith(fv);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid Ve <-> Ev format at line " + lineCount, e);
            }
        }
        lineCount++;

        line = reader.readLine(); lineCount++;
        if (line == null)
            throw new IOException("Unexpected end of file after vertices section");
        if (!line.equals("-- Ef <-> Fe --"))
            throw new IOException("Expected Ef <-> Fe section header at line " + lineCount);

        while (!(line = reader.readLine()).isEmpty()){
            lineCount++;

            String[] effeStr = line.split(" ");
            if (effeStr.length != 2)
                throw new IOException("Expected 2 values for Ef <-> Fe at line " + lineCount);
            try {
                int efIndex = Integer.parseInt(effeStr[0]);
                int feIndex = Integer.parseInt(effeStr[1]);
                Ef ef = indexToEf.get(efIndex);
                Fe fe = indexToFe.get(feIndex);
                ef.pairWith(fe);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid Ve <-> Ev format at line " + lineCount, e);
            }
        }

        return new Medium(height, width, vertices, edges, faces, ves, vfs, evs, efs, fvs, fes);
    }
}
