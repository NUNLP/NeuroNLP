import clinicalnlp.pattern.AnnotationSequencer
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token
import edu.northwestern.fsm.type.Measure
import edu.northwestern.fsm.type.SDH
import edu.northwestern.fsm.type.Section
import edu.northwestern.fsm.type.Side

import static clinicalnlp.dsl.DSL.getContains
import static edu.northwestern.fsm.domain.SDHConcept.SEG_FINDINGS
import static edu.northwestern.fsm.domain.SDHConcept.SEG_IMPRESSION
import static edu.northwestern.fsm.logic.Functions.createConceptMentions

//---------------------------------------------------------------------------------------------------------------------
// base pattern matching
//---------------------------------------------------------------------------------------------------------------------

createConceptMentions(
    patterns:sdh$patterns,
    jcas:jcas,
    searchSet:jcas.select(type:Section, filter:{it.divType in [SEG_FINDINGS, SEG_IMPRESSION]}),
    type:SDH,
    longestMatch:true
)

createConceptMentions(
    patterns:side$patterns,
    jcas:jcas,
    searchSet:jcas.select(type:Section, filter:{it.divType in [SEG_FINDINGS, SEG_IMPRESSION]}),
    type:Side,
    longestMatch:true
)

//---------------------------------------------------------------------------------------------------------------------
// higher order pattern matching
//---------------------------------------------------------------------------------------------------------------------

jcas.select(type:Sentence, filter:(contains(SDH))).each { Sentence sentence ->
    def sequence = new AnnotationSequencer(sentence, [SDH, Side]).first()
    sdh$side$relation$1.matcher(sequence).each { Binding b ->
        SDH sdh = b.getVariable('sdh')[0]
        Side side = b.getVariable('side')[0]
        sdh.side = side
    }
    sdh$side$relation$2.matcher(sequence).each { Binding b ->
        SDH sdh = b.getVariable('sdh')[0]
        Side side = b.getVariable('side')[0]
        sdh.side = side
    }

    sequence = new AnnotationSequencer(sentence, [Token]).first()
    sdh$size$relation$0.matcher(sequence).each { Binding b ->
        Token num$unit = b.getVariable('numunit')[0]
        def m = num$unit.coveredText =~ /(?i)(.+)(cm|mm)/
        m.find()
        String num = m.group(1)
        String unit = m.group(2)
        jcas.create(type:Measure,
            begin:num$unit.begin,
            end:num$unit.end,
            identifier:unit.toUpperCase(),
            value:num.toFloat())
    }
    sdh$size$relation$1.matcher(sequence).each { Binding b ->
        Token num = b.getVariable('num')[0]
        Token unit = b.getVariable('unit')[0]
        jcas.create(type:Measure,
            begin:num.begin,
            end:num.end,
            identifier:unit.coveredText.toUpperCase(),
            value:num.coveredText.toFloat())
    }

    sequence = new AnnotationSequencer(sentence, [SDH, Side, Measure]).first()
    sdh$size$relation$2.matcher(sequence).each { Binding b ->
        SDH sdh = b.getVariable('sdh')[0]
        Measure measure = b.getVariable('measure')[0]
        sdh.thickness = measure
    }
    sdh$size$relation$3.matcher(sequence).each { Binding b ->
        SDH sdh = b.getVariable('sdh')[0]
        Measure measure = b.getVariable('measure')[0]
        sdh.thickness = measure
    }
}
