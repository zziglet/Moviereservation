package member;

import movie.Movie;

import java.io.*;

public class MemberRepository {

    public MemberRepository() {
    }

    public void SaveMember(String name, String id, String pw){
        /*
            회원가입 시에 id.txt 파일에 회원 정보 저장 -> menu에서 id,pw,이름 입력받고 각 변수들은 넘김
            이 함수를 통해 생성되는 파일은 항상 새로운 파일
         */
        try{
            String path =  "src/user/"+id+".txt";
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            String str = name + " " + id + " " + pw;
            writer.write(str);
            writer.close();
        } catch (IOException e){
            e.getStackTrace();
        }
    }

    public void SaveMovie(Member member, Movie movie){
        String path =  "src/user/"+member.getId()+".txt";
        File file = new File(path);

    }
    public Member find(String id){


    }
}
