package edu.northwestern.fsm.service

import au.com.bytecode.opencsv.CSVWriter
import clinicalnlp.types.NamedEntityMention
import edu.northwestern.fsm.pipeline.SDHExtractionPipelineGenerator
import org.apache.uima.analysis_engine.AnalysisEngine
import org.apache.uima.analysis_engine.AnalysisEngineProcessException
import org.apache.uima.fit.factory.AggregateBuilder
import org.apache.uima.jcas.JCas

class SDHExtractionApp {

    AnalysisEngine engine
	JCas jcas

    /**
     * Constructor
     */
	SDHExtractionApp() {
        try {
            AggregateBuilder builder = SDHExtractionPipelineGenerator.breastPathologyPipeline()
            this.engine = builder.createAggregate()
            this.jcas = engine.newJCas()
        }
        catch (Exception e) { e.printStackTrace() }
    }

    /**
     * Text processing method
     * @param text
     * @return
     */
	NLPResults process(File file) {
		NLPResults results = new NLPResults()
		try {
			this.jcas.reset()
			this.jcas.setDocumentText(file.text)
			this.engine.process(jcas)

			jcas.select(type:NamedEntityMention).each { NamedEntityMention nem ->
                NLPResult result = new NLPResult()
                result.spanStart = nem.begin
                result.spanEnd = nem.end
                result.spannedText = nem.coveredText
                result.mentionClass = nem.code
                result.cite = nem.cite
                result.assertion = nem.polarity
                results.results.add(result)
            }
		} catch (AnalysisEngineProcessException e) {
			e.printStackTrace()
		}
		return results
	}

	/**
	 * Run the app
	 * @param args
	 */
	static void main(String[] args) {
        File directory = new File(args[0])
        File csvFile = new File(args[1])
        if (!directory.exists()) {
            System.out.println("directory does not exist")
            System.exit(1)
        }
        File [] files = directory.listFiles(new FilenameFilter() {
            @Override
            boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        })

        SDHExtractionApp app = new SDHExtractionApp()
        Writer writer = new FileWriter(csvFile)
        def w = new CSVWriter(writer)
        w.writeNext((String[]) [
            'textSource',
            'spanStart',
            'spanEnd',
            'spannedText',
            'cite',
            'mentionClass',
            'assertion'
        ])

        files.each { File file ->
            NLPResults results = app.process(file)
            results.results.each { NLPResult result ->
                w.writeNext((String[])[
                    file.name.hashCode(),
                    result.spanStart,
                    result.spanEnd,
                    result.spannedText,
                    result.cite,
                    result.mentionClass,
                    result.assertion
                ])
            }
        }

        writer.close()
	}
}
