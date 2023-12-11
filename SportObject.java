public class SportObject {
    public String name;
    public String subjectRF;
    public SportObject(String name, String subjectRF){
        name = name.substring(0,name.length()-1);
        subjectRF = subjectRF.substring(0, subjectRF.length() - 1);
        if (subjectRF.equals("г. Москва")){
            subjectRF = "Москва";
        }
        this.name = name;
        this.subjectRF = subjectRF;
    }
    public String getName(){
        return name;
    }
    public String getSubjectRF(){
        return subjectRF;
    }
}
