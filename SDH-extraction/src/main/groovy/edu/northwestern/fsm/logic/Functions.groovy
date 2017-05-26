package edu.northwestern.fsm.logic

import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.tcas.Annotation

import java.util.regex.Matcher
import java.util.regex.Pattern

import static clinicalnlp.dsl.DSL.*

class Functions {
        /**
         * Create concept mentions
         * @param args
         * @return
         */
        static Collection<NamedEntity> createConceptMentions(final Map args) {
            JCas jcas = args.jcas
            Map patterns = args.patterns
            Collection searchSet = args.searchSet
            Class type = args.type
            Boolean longestMatch = args.longestMatch
            Integer group = args.group ?: 0
            List<? extends NamedEntity> mentions = []
            searchSet.each { Annotation ann ->
                patterns.each { Pattern pattern, Map vals ->
                    Matcher matcher = ann.coveredText =~ pattern
                    matcher.each {
                        // create an annotation for each match
                        NamedEntity mention = jcas.create(
                            type:type,
                            begin:(matcher.start(group) + ann.begin),
                            end:(matcher.end(group) + ann.begin),
                            identifier:vals.code
                        )
                        mentions << mention
                    }
                }
            }
            if (longestMatch) {
                removeCovered(jcas, [anns:jcas.select(type:type), types:[type]])
            }
            return mentions
        }
}
