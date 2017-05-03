package edu.northwestern.fsm.service

import clinicalnlp.types.NamedEntityMention
import edu.northwestern.fsm.pipeline.SDHExtractionPipelineGenerator
import org.apache.uima.analysis_engine.AnalysisEngine
import org.apache.uima.analysis_engine.AnalysisEngineProcessException
import org.apache.uima.fit.factory.AggregateBuilder
import org.apache.uima.jcas.JCas
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@RequestMapping(value="nlpservice/breast")
class SDHExtractionService extends SpringBootServletInitializer {

    AnalysisEngine engine
	JCas jcas

    /**
     * Constructor
     */
	SDHExtractionService() {
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
	@RequestMapping(
	    method = RequestMethod.POST,
        value="/process",
		consumes="text/plain",
        produces="application/json")
	@ResponseBody NLPResults process(@RequestBody String text) {
		NLPResults results = new NLPResults()
		synchronized (this) {
			try {
				this.jcas.reset()
				this.jcas.setDocumentText(text)
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
		}
		return results
	}

	/**
	 * Run the web service
	 * @param args
	 */
	static void main(String[] args) {
		SpringApplication.run(SDHExtractionService, args)
	}
}
