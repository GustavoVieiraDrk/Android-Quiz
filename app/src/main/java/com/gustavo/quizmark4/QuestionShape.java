package com.gustavo.quizmark4;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionShape implements Parcelable {
    private final String question, a, b, c, d;
    private final int correctAnswer;

    public QuestionShape(String question, String a, String b, String c, String d, int correctAnswer) {
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correctAnswer = correctAnswer;
    }

    protected QuestionShape(Parcel in) {
        question = in.readString();
        a = in.readString();
        b = in.readString();
        c = in.readString();
        d = in.readString();
        correctAnswer = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(a);
        dest.writeString(b);
        dest.writeString(c);
        dest.writeString(d);
        dest.writeInt(correctAnswer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuestionShape> CREATOR = new Creator<QuestionShape>() {
        @Override
        public QuestionShape createFromParcel(Parcel in) {
            return new QuestionShape(in);
        }

        @Override
        public QuestionShape[] newArray(int size) {
            return new QuestionShape[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
