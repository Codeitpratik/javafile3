import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String questionText;
    String[] options;
    int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public boolean isCorrect(int answer) {
        return answer == correctAnswer;
    }
}

public class Quiz {
    static ArrayList<Question> questions = new ArrayList<>();
    static int score = 0;
    static int currentQuestionIndex = 0;
    static boolean answered = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample questions
        questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 3));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 2));
        questions.add(new Question("What is the largest planet in our solar system?", new String[]{"Earth", "Jupiter", "Mars", "Saturn"}, 2));

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered) {
                    System.out.println("Time's up!");
                    nextQuestion();
                }
            }
        }, 10000); // 10 seconds for each question

        while (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            currentQuestion.displayQuestion();

            System.out.print("Enter your answer (1-4): ");
            int answer = scanner.nextInt();
            answered = true;

            if (currentQuestion.isCorrect(answer)) {
                score++;
            }

            nextQuestion();
        }

        displayResults();
        scanner.close();
    }

    public static void nextQuestion() {
        currentQuestionIndex++;
        answered = false;
        if (currentQuestionIndex < questions.size()) {
            System.out.println("\nNext question:\n");
        }
    }

    public static void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your score: " + score + "/" + questions.size());
        System.out.println("Summary of correct/incorrect answers:");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + ". " + q.questionText + " - " + (q.isCorrect(i + 1) ? "Correct" : "Incorrect"));
        }
    }
}

    
