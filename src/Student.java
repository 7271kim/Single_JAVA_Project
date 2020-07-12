public class Student  implements Comparable<Student> {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    @Override
    public int compareTo(Student input) {
        int result = Integer.compare(score, input.score);
        if( result == 0 ) {
            result = input.name.compareTo(name);
        }
        return result;
    }

    
    @Override
    public String toString() {
        return "이름 : " + name + " 점수 : " + score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
}