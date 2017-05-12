package edu.northwestern.fsm.service

import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity
import edu.northwestern.fsm.pipeline.SDHExtractionPipelineGenerator
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
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
            AggregateBuilder builder = SDHExtractionPipelineGenerator.sdhPipeline()
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

			jcas.select(type:NamedEntity).each { NamedEntity nem ->
                NLPResult result = new NLPResult()
                result.spanStart = nem.begin
                result.spanEnd = nem.end
                result.spannedText = nem.coveredText
                result.mentionClass = nem.value
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

        String filename = '/Users/willthompson/Box Sync/SDH NLP Development Project/SDH coding.xlsx'
        FileInputStream excelFile = new FileInputStream(new File(filename))
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0)
        Iterator<Row> iterator = datatypeSheet.iterator()

        Row currentRow = iterator.next()
        Iterator<Cell> cellIterator = currentRow.iterator()
        while (cellIterator.hasNext()) {
            Cell currentCell = cellIterator.next()
            if (currentCell.getCellTypeEnum() == CellType.STRING) {
                System.out.print(currentCell.getStringCellValue() + "--");
            } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                System.out.print(currentCell.getNumericCellValue() + "--");
            }
        }

        while (iterator.hasNext()) {
            currentRow = iterator.next()
            cellIterator = currentRow.iterator()
            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next()
//                if (currentCell.getCellTypeEnum() == CellType.STRING) {
//                    System.out.print(currentCell.getStringCellValue() + "--");
//                } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
//                    System.out.print(currentCell.getNumericCellValue() + "--");
//                }
            }
        }

//        File directory = new File(args[0])
//        File csvFile = new File(args[1])
//        if (!directory.exists()) {
//            System.out.println("directory does not exist")
//            System.exit(1)
//        }
//        File [] files = directory.listFiles(new FilenameFilter() {
//            @Override
//            boolean accept(File dir, String name) {
//                return name.endsWith(".txt");
//            }
//        })
//
//        SDHExtractionApp app = new SDHExtractionApp()
//        Writer writer = new FileWriter(csvFile)
//        def w = new CSVWriter(writer)
//        w.writeNext((String[]) [
//            'textSource',
//            'spanStart',
//            'spanEnd',
//            'spannedText',
//            'cite',
//            'mentionClass',
//            'assertion'
//        ])
//
//        files.each { File file ->
//            NLPResults results = app.process(file)
//            results.results.each { NLPResult result ->
//                w.writeNext((String[])[
//                    file.name.hashCode(),
//                    result.spanStart,
//                    result.spanEnd,
//                    result.spannedText,
//                    result.cite,
//                    result.mentionClass,
//                    result.assertion
//                ])
//            }
//        }
//
//        writer.close()
	}
}
