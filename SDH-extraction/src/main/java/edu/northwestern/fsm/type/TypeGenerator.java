package edu.northwestern.fsm.type;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Div;
import org.apache.uima.resource.metadata.Import;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.resource.metadata.impl.Import_impl;
import org.apache.uima.resource.metadata.impl.TypeSystemDescription_impl;
import org.apache.uima.tools.jcasgen.Jg;
import org.apache.uima.util.FileUtils;

import java.io.File;
import java.io.FileWriter;


public class TypeGenerator {
    public static void main(String[] args) throws Exception {
        // set up directories for generated source
        FileUtils.deleteRecursive(new File("generated-types"));
        File file = new File("generated-types/src");
        file.delete();
        File srcDir = new File("generated-types/src");
        srcDir.mkdirs();
        File classDir = new File("generated-types/classes");
        classDir.mkdirs();
        File resDir = new File("generated-types/resources/edu/northwestern/fsm/type");
        resDir.mkdirs();

        // create the type system
        TypeSystemDescription types = new TypeSystemDescription_impl();
        Import nerImport = new Import_impl();
        nerImport.setName("desc/type/NamedEntity");
        Import divImport = new Import_impl();
        divImport.setName("desc/type/LexicalUnits");
        Import metadataImport = new Import_impl();
        metadataImport.setName("desc/type/metadata_customized");

        Import[] imports = new Import[] { nerImport, divImport, metadataImport };
        types.setImports(imports);

        types.addType("edu.northwestern.fsm.type.Section",
            "Section annotation",
            Div.class.getCanonicalName());

        types.addType("edu.northwestern.fsm.type.Side",
            "Side annotation",
            NamedEntity.class.getCanonicalName());

        TypeDescription measureType = types.addType("edu.northwestern.fsm.type.Measure",
            "Measure annotation",
            NamedEntity.class.getCanonicalName());

        TypeDescription midlineShiftType = types.addType("edu.northwestern.fsm.type.MidlineShift",
            "Measure annotation",
            NamedEntity.class.getCanonicalName());
        midlineShiftType.addFeature("distance", "", "edu.northwestern.fsm.type.Measure");

        TypeDescription sdhType = types.addType("edu.northwestern.fsm.type.SDH",
            "SDH annotation",
            NamedEntity.class.getCanonicalName());
        sdhType.addFeature("side", "", "edu.northwestern.fsm.type.Side");
        sdhType.addFeature("thickness", "", "edu.northwestern.fsm.type.Measure");
        sdhType.addFeature("convexity", "", "uima.cas.Boolean");

        TypeDescription docType = types.addType("edu.northwestern.fsm.type.SDHSummary",
            "SDH summary",
            DocumentMetaData.class.getCanonicalName());
        docType.addFeature("side", "", "uima.cas.Integer");
        docType.addFeature("thickness", "", "uima.cas.Integer");
        docType.addFeature("convexity", "", "uima.cas.Boolean");
        docType.addFeature("shift", "", "uima.cas.Integer");
        docType.addFeature("count", "", "uima.cas.Integer");

        // generate an XML descriptor
        FileWriter writer = new FileWriter(new File(resDir.getCanonicalPath() + "/sdh-types.xml"));
        types.toXML(writer);

        // generate the source files
        Jg jcasGen = new Jg();
        String[] args2 = new String[]{"-jcasgeninput", resDir.getCanonicalPath() + "/sdh-types.xml",
            "-jcasgenoutput", srcDir.getCanonicalPath(),
            "=jcasgenclasspath", classDir.getCanonicalPath(),
            "-limitToDirectory", resDir.getCanonicalPath()
        };
        jcasGen.main1(args2);
    }
}
