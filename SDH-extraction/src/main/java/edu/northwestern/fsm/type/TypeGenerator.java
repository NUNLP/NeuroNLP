package edu.northwestern.fsm.type;

import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity;
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
        Import[] imports = new Import[] { nerImport };
        types.setImports(imports);

        TypeDescription sectionType = types.addType("edu.northwestern.fsm.type.Section",
            "Section annotation",
            "uima.tcas.Annotation");
        sectionType.addFeature("divType", "Section type", "uima.cas.String");

        TypeDescription dictType = types.addType("edu.northwestern.fsm.type.DictMatch",
            "Dictionary match annotation",
            "uima.tcas.Annotation");
        dictType.addFeature("code", "Code", "uima.cas.String");

        // generate an XML descriptor
        FileWriter writer = new FileWriter(new File(resDir.getCanonicalPath() + "/dkpro-types.xml"));
        types.toXML(writer);

        // generate the source files
        Jg jcasGen = new Jg();
        String[] args2 = new String[]{"-jcasgeninput", resDir.getCanonicalPath() + "/dkpro-types.xml",
            "-jcasgenoutput", srcDir.getCanonicalPath(),
            "=jcasgenclasspath", classDir.getCanonicalPath(),
            "-limitToDirectory", resDir.getCanonicalPath()
        };
        jcasGen.main1(args2);
    }
}
