package edu.nur.homework03.util;

import edu.nur.homework03.model.Answer;
import edu.nur.homework03.model.Question;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component
public final class QuestionConverterCsv implements QuestionConverter {

    @Override
    public List<Question> convertToQuestions(List<String[]> lines) {

        Map<Long, Question> questionMap = new HashMap<>();
        lines.forEach(line -> {

            long questionId = Long.parseLong(line[0]);
            String questionTitle = line[1];
            long answerId = Long.parseLong(line[2]);
            String answerTitle = line[3];
            boolean isCorrect = Boolean.parseBoolean(line[4]);

            Answer answer = new Answer(answerId, answerTitle, isCorrect);

            if (!questionMap.containsKey(questionId)) {
                Question question = new Question(questionId, questionTitle, new HashSet<>());
                questionMap.put(questionId, question);
            }
            questionMap.get(questionId).getAnswers().add(answer);
        });

        return questionMap.values().stream().toList();
    }

}
