package edu.nur.util;

import edu.nur.model.Answer;
import edu.nur.model.Question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public final class QuestionConverterCsvImpl implements QuestionConverter {

    private static QuestionConverterCsvImpl instance;

    private QuestionConverterCsvImpl() {
    }

    public static QuestionConverterCsvImpl getInstance() {
        if (instance == null) {
            instance = new QuestionConverterCsvImpl();
        }

        return instance;
    }

    @Override
    public List<Question> convertToQuestion(List<String[]> lines) {

        Map<Long, Question> questionMap = new HashMap<>();
        lines.forEach(line -> {

            long questionId      = Long.parseLong(line[0]);
            String questionTitle = line[1];
            long correctAnswerId = Long.parseLong(line[2]);
            long answerId        = Long.parseLong(line[3]);
            String answerTitle   = line[4];

            Answer answer = new Answer(answerId, answerTitle);

            if (!questionMap.containsKey(questionId)) {
                Question question = new Question(questionId, questionTitle, new HashSet<>(), correctAnswerId);
                questionMap.put(questionId, question);
            }
            questionMap.get(questionId).getAnswers().add(answer);
        });

        return questionMap.values().stream().toList();
    }

}
