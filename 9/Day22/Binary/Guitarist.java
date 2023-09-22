public class Guitarist {
    private int no;
    private String name;
    private String teamName;
    public String guitar;

    public Guitarist(Builder builder) {
        this.no = builder.no;
        this.name = builder.name;
        this.teamName = builder.teamName;
        this.guitar = builder.guitar;
    }

    public String toString() {
        return this.no + ", " + this.name + ", " +this.teamName + ", " + this.guitar;
    }

    /* public Guitarist(int no) {
        this.no = no;
    }

    public Guitarist(int no, String name) {
        this(no);
        this.no = no;
    }

    public Guitarist(int no, String name, String teamName) {
        this(no, teamName);
        this.teamName = teamName;
    } */

    public static class Builder {
        private int no;
        private String name;
        private String teamName;
        private String guitar;

        public Builder no(int no){
            this.no = no;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder teamName(String teamName){
            this.teamName = teamName;
            return this;
        }

        public Builder guitar(String guitar){
            return this;
        }

        public Guitarist build() {
            return new Guitarist(this);
        }
    }

}


class Test{
    public static void main(String[] args) {
        Guitarist guitarist = new Guitarist.Builder()
            .no(1)                              // guitarist.no(1);
            .name("Randy Rhoads")
            .teamName("Quite Riot")
            .guitar("Les paul")
            .build();
        System.out.println(guitarist);
    }

    // iterator - next, hasnext를 사용
}