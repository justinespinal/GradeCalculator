
public class Grade {
    String grade;
    int numGrade;

    

    public Grade(int n)
    {
        if(n<0) throw new IllegalGradeException("Illegal grade!");
        numGrade = n;
        grade = toGrade(n);
    }
    public Grade(String s)
    {
        if(!s.matches("A[+]?||A[-]?||B[+]?||B[-]?||C[+]?||C[-]?||D[+]?||F"))
        {
            throw new IllegalGradeException("Illegal Letter Grade");
        }
        grade = s;
        numGrade = toNum(s);
    }

    public String getGrade()
    {
        return grade;
    }

    public double getGPA()
    {
        if(grade.equals("A+")) return 4.0;
        else if(grade.equals("A")) return 4.0;
        else if(grade.equals("A")) return 3.7;
        else if(grade.equals("B+")) return 3.3;
        else if(grade.equals("B")) return 3.0;
        else if(grade.equals("B-")) return 2.7;
        else if(grade.equals("C+")) return 2.3;
        else if(grade.equals("C")) return 2.0;
        else if(grade.equals("C-")) return 1.7;
        else if(grade.equals("D+")) return 1.3;
        else if(grade.equals("D")) return 1.0;
        else if(grade.equals("F")) return 0.0;
        else return 0.0;
    }

    public int getNum()
    {
        return numGrade;
    }

    public void setGrade(String s)
    {
        if(!s.matches("A[+]?||A[-]?||B[+]?||B[-]?||C[+]?||C[-]?||D[+]?||F"))
        {
            throw new IllegalGradeException("Illegal Letter Grade");
        }
        grade = s;
        numGrade = toNum(s);
    }

    public void setNum(int n)
    {
        if(n<0) 
        {
            throw new IllegalGradeException("Illegal grade!");
        }
        numGrade = n;
        grade = toGrade(n);
    }

    public String toGrade(int n){
        
        if(n>=97)
        {
            return "A+";
        }
        else if(n>=93 && n<=96)
        {
            return "A";
        }
        else if(n>=90 && n<=92)
        {
            return "A-";
        }
        else if(n>=87 && n<=98)
        {
            return "B+";
        }
        else if(n>=83 && n<=86)
        {
            return "B";
        }
        else if(n>=80 && n<=82)
        {
            return "B-";
        }
        else if(n>=77 && n<=79)
        {
            return "C+";
        }
        else if(n>=73 && n<=76)
        {
            return "C";
        }
        else if(n>=70 && n<=72)
        {
            return "C-";
        }
        else if(n>=67 && n<=69)
        {
            return "D+";
        }
        else if(n>=60 && n<=66)
        {
            return "D";
        }
        else
        {
            return "F";
        }
        
    }

    public int toNum(String s)
    {
        if(s.equals("A+"))
        {
            return 97;
        }
        else if(s.equals("A"))
        {
            return 93;
        }
        else if(s.equals("A-"))
        {
            return 90;
        }
        else if(s.equals("B+"))
        {
            return 87;
        }
        else if(s.equals("B"))
        {
            return 83;
        }
        else if(s.equals("B-"))
        {
            return 80;
        }
        else if(s.equals("C+"))
        {
            return 77;
        }
        else if(s.equals("C"))
        {
            return 73;
        }
        else if(s.equals("C-"))
        {
            return 70;
        }
        else if(s.equals("D+"))
        {
            return 67;
        }
        else if(s.equals("D"))
        {
            return 60;
        }
        else
        {
            return 0;
        }
    }

    public String toString()
    {
        return grade;
    }

}
