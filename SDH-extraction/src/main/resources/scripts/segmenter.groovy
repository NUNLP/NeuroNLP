import clinicalnlp.pattern.AnnotationPattern
import clinicalnlp.pattern.AnnotationRegex
import clinicalnlp.pattern.AnnotationSequencer
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Heading
import edu.northwestern.fsm.type.Section
import org.apache.uima.jcas.tcas.DocumentAnnotation

import java.util.regex.Matcher
import java.util.regex.Pattern

import static clinicalnlp.pattern.AnnotationPattern.*

headings = [
    (~/HISTORY:/)                 : 'HISTORY',
    (~/FINDINGS:/)                : 'FINDINGS',
    (~/IMPRESSION:/)              : 'IMPRESSION',
    (~/(PROVIDERS|RADIOLOGISTS):/): 'PROVIDERS'
]
headings.each { Pattern pat, String type ->
    Matcher matcher = jcas.documentText =~ pat
    matcher.each {
        jcas.create(type: Heading, begin: matcher.start(0), end: matcher.end(0), divType: type)
    }
}

AnnotationRegex segPattern1 = new AnnotationRegex((AnnotationPattern)
    $N('h1', $A(Heading)) & $N('h2', $A(Heading)) >> (true)
)

AnnotationRegex segPattern2 = new AnnotationRegex((AnnotationPattern)
    $N('h1', $A(Heading)) & $N('h2', $A(Heading)) >> (false)
)

jcas.select(type: DocumentAnnotation).each { DocumentAnnotation docAnn ->
    def sequence = new AnnotationSequencer(docAnn, [Heading]).first()
    segPattern1.matcher(sequence).each { Binding b ->
        Heading h1 = b.getVariable('h1')[0]
        Heading h2 = b.getVariable('h2')[0]
        jcas.create(type:Section,
            begin:h1.end,
            end:h2.begin,
            divType:h1.divType
        )
    }
    segPattern2.matcher(sequence).each { Binding b ->
        Heading h1 = b.getVariable('h1')[0]
        jcas.create(type:Section,
            begin:h1.end,
            end:jcas.documentText.length(),
            divType:h1.divType
        )
    }
}

