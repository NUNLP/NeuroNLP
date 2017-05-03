package edu.northwestern.fsm.logic

import clinicalnlp.types.CompositeNamedEntity
import clinicalnlp.types.NamedEntityMention
import edu.northwestern.fsm.domain.SDHConcept
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.apache.uima.jcas.tcas.Annotation

import java.util.regex.Matcher
import java.util.regex.Pattern

import static edu.northwestern.fsm.domain.SDHConcept.*

class Functions {
    /**
     * Merge CompositeNamedEntity mentions into a single one (check on semClass)
     * @param first
     * @param rest
     * @return
     */
    static CompositeNamedEntity mergeComposites(JCas jcas, CompositeNamedEntity first, Collection<Annotation> rest) {
        Collection<CompositeNamedEntity> mentions = [first]
        mentions.addAll(rest.findAll {it.typeIndexID == CompositeNamedEntity.typeIndexID})
        CompositeNamedEntity composite = jcas.create(type:CompositeNamedEntity,
            begin: first.begin,
            end: mentions.last().end,
            code: SDHConcept.code,
            codeSystem:CODING_SCHEME_SNOMED,
            semClass:first.semClass
        )
        composite.mentions = new FSArray(jcas, mentions.size())
        mentions.eachWithIndex { cne, index ->
            composite.mentions.set(index, cne.mentions.get(0))
        }
        return composite
    }

    /**
     * Create NamedEntityMention instances using regular expression patterns
     * @param args
     * @return
     */
    static Collection<NamedEntityMention> createConceptMentions(Map args) {
        JCas jcas = args.jcas
        Map patterns = args.patterns
        Collection searchSet = args.searchSet
        Class type = args.type
        Boolean longestMatch = args.longestMatch
        Integer group = args.group ?: 0

        List<? extends NamedEntityMention> mentions = []
        searchSet.each { Annotation ann ->
            patterns.each { Pattern pattern, Map vals ->
                Matcher matcher = ann.coveredText =~ pattern
                matcher.each {
                    // create an annotation for each match
                    NamedEntityMention mention = jcas.create(
                        type:type,
                        begin:(matcher.start(group) + ann.begin),
                        end:(matcher.end(group) + ann.begin),
                        cite:vals.cite,
                        code:vals.code,
                        codeSystem:vals.codeSystem,
                        semClass:vals.tui,
                    )
                    mentions << mention
                }
            }
        }

        if (longestMatch) {
            jcas.removeCovered(
                anns:jcas.select(type:type),
                types:[type]
            )
        }

        return mentions
    }
}
