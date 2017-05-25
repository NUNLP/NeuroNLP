import clinicalnlp.pattern.AnnotationPattern
import clinicalnlp.pattern.AnnotationRegex
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Heading

import static clinicalnlp.pattern.AnnotationPattern.*

headings = [
    (~/HISTORY:/)                 : 'HISTORY',
    (~/FINDINGS:/)                : 'FINDINGS',
    (~/IMPRESSION:/)              : 'IMPRESSION',
    (~/(PROVIDERS|RADIOLOGISTS):/): 'PROVIDERS'
]


AnnotationRegex segPattern1 = new AnnotationRegex((AnnotationPattern)
    $N('h1', $A(Heading)) & $N('h2', $A(Heading)) >> (true)
)

AnnotationRegex segPattern2 = new AnnotationRegex((AnnotationPattern)
    $N('h1', $A(Heading)) & $N('h2', $A(Heading)) >> (false)
)

// ---------------------------------------------------------------------------------------------------------------------
// binding variable map
// ---------------------------------------------------------------------------------------------------------------------
[
    headings:headings,
    segPattern1:segPattern1,
    segPattern2:segPattern2
]

