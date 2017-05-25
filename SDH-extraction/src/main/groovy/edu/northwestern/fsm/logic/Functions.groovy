package edu.northwestern.fsm.logic

import clinicalnlp.dsl.DSL
import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.tcas.Annotation

import java.util.regex.Matcher
import java.util.regex.Pattern

class Functions {
    /**
     * Create NamedEntity instances using regular expression patterns
     * @param args
     * @return
     */
    static Collection<NamedEntity> createConceptMentions(Map args) {
        JCas jcas = args.jcas
        Map patterns = args.patterns
        Collection searchSet = args.searchSet
        Class type = args.type
        Integer group = args.group ?: 0
        String identifier = args.identifier

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
                        identifier:identifier
                    )
                    mentions << mention
                }
            }
        }

        return mentions
    }
}
