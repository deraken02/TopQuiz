package model;

import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private final List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> mQuestionList) {

        this.mQuestionList = mQuestionList;
        Collections.shuffle(this.mQuestionList);
        this.mNextQuestionIndex = 0;
    }

    public Question getCurrentQuestion(){
        return mQuestionList.get(mNextQuestionIndex);
    }
    public Question getNextQuestion() {
        mNextQuestionIndex =(++mNextQuestionIndex)%mQuestionList.size();
        return getCurrentQuestion();
    }
}
