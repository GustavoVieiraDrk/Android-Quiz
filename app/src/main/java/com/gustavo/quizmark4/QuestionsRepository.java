package com.gustavo.quizmark4;

import android.os.Parcel;
import android.os.Parcelable;
import android.service.notification.NotificationListenerService;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionsRepository implements Parcelable {

    private static final QuestionsRepository INSTANCE = new QuestionsRepository();
    private final List<QuestionShape> questionShapeList;

    public QuestionsRepository() {

        questionShapeList = new ArrayList<>();

        questionShapeList.add(new QuestionShape("Qual Destes Personagens não tem Nariz?",
                "Bulma", "Goku", "Kuririn", "Yamcha", 3));

        questionShapeList.add(new QuestionShape("Quantos planetas formam o nosso sistema solar?",
                "8 Planetas", "6 planetas", "5 planetas", "7 planetas", 1));

        questionShapeList.add(new QuestionShape("Na seria Game of Thrones Jon Snow na verdade pertence a casa?",
                "Baratheon", "Greyjoy", "Stark", "Targryen", 4));

        questionShapeList.add(new QuestionShape("Qual o nome do jogo mais vendido de Super Nintendo?",
                "Top Gear", "Super Mario World", "Super metroid", "Mortal kombat", 2));

        questionShapeList.add(new QuestionShape("quando numeros 9 existem de 1 a 100?",
                "20", "11", "30", "21", 1));

        questionShapeList.add(new QuestionShape("De quem é a famosa frase “Penso, logo existo”?",
                "Platão", "Galileu Galilei", "Descartes", "Sócrates", 3));

        questionShapeList.add(new QuestionShape("De onde é a invenção do chuveiro elétrico?",
                "França", "Inglaterra", "Brasil", "Austrália", 3));

        questionShapeList.add(new QuestionShape("Qual o menor e o maior país do mundo?",
                "Vaticano e Rússia", "Nauru e China", "Mônaco e Canadá", "Malta e Estados Unidos", 1));

        questionShapeList.add(new QuestionShape("Qual o nome do presidente do Brasil que ficou conhecido como Jango?",
                "Jânio Quadros", "Jacinto Anjos", "Getúlio Vargas", "João Goulart", 4));

        questionShapeList.add(new QuestionShape("Qual o livro mais vendido no mundo a seguir à Bíblia?",
                "O Senhor dos Anéis", "Dom Quixote", "O Pequeno Príncipe", "Ela, a Feiticeira", 2));

    }


    public static QuestionsRepository getInstance() {
        return INSTANCE;
    }


//    public void generateNewRandomList() {
//        final Random random = new Random();
//        questionIndex.clear();
//        for (int i = 0; i < 5; i += 1) {
//            int index;
//            while (true) {
//                index = random.nextInt(questionShapeList.size());
//                if (!questionIndex.contains(index)) {
//                    questionIndex.add(index);
//                    break;
//                }
//            }
//        }
//    }

    protected QuestionsRepository(Parcel in) {
        questionShapeList = in.createTypedArrayList(QuestionShape.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(questionShapeList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuestionsRepository> CREATOR = new Creator<QuestionsRepository>() {
        @Override
        public QuestionsRepository createFromParcel(Parcel in) {
            return new QuestionsRepository(in);
        }

        @Override
        public QuestionsRepository[] newArray(int size) {
            return new QuestionsRepository[size];
        }
    };

    public QuestionShape getNextQuestion(int aux) {
        return questionShapeList.get(aux);
    }
}
