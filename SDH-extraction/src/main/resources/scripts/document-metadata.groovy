import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData
import edu.northwestern.fsm.type.SDH
import edu.northwestern.fsm.type.SDHSummary

import static edu.northwestern.fsm.domain.SDHConcept.*

SDHSummary summary = jcas.select(type:SDHSummary)[0]
if (summary) {
    Collection<SDH> sdhs = jcas.select(type:SDH)
    sdhs.each { SDH sdh ->
        if (!sdh.side) { summary.side = 3 }
        else {
            switch (sdh.side.identifier) {
                case LEFT.code:
                    summary.side = 1
                    break
                case RIGHT.code:
                    summary.side = 2
                    break
            }
        }
    }
}
