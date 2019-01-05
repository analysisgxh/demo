package ch07;

/**
 * 例7.1 学生信息类
 */
public class Student {

    int id;        //学号
    String name;   //姓名
    double score;  //成绩
/*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
*/
    public Student(int id, String name, double score) { //构造方法
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
