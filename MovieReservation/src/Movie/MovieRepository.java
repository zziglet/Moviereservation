package Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieRepository {


    public void saveMovietxt(Movie movie, String seat) {

    }

    public void DeleteMovietxt(Movie movie, String seat) throws IOException {
        //tempFile이라는 임시 저장 파일을 만들어서 좌석 번호 삭제 후 임시 파일에 저장 -> 임시 파일을 기존 파일로 변경
        File inputFile = new File("movie.txt");
        File tempFile = new File("temp_movie.txt");

        //좌석 번호들 리스트로
        List<String> seatsCancelList=Arrays.asList(seat.split("\\s+"));

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String line;
                String movieInfo=String.format("%s %s %s %s %s", movie.getTheater(), movie.getName(), movie.getDate(), movie.getStart(), movie.getEnd());

                while((line=reader.readLine())!=null){
                    if(line.startsWith(movieInfo)){
                        //movie 정보랑 seat 구분
                        List<String> part= new ArrayList<>(Arrays.asList(line.split(" ")));
                        //취소된 좌석 삭제
                        part.removeAll(seatsCancelList);
                        //정보 다시 합치기
                        String afterline=String.join(" ", part);
                        writer.write(afterline);

                    }else {
                        writer.write(line);
                    }
                    writer.newLine();
                }
        }

        //수정된 파일로 기존 파일 업데이트
        //원본 파일 삭제 실패 시 error handling
        if(!inputFile.delete()){
            System.out.println("..! file delete fail");
            return;
        }

        if(!tempFile.renameTo(inputFile)){
            System.out.println("..! Cannot update the file");
        }
    }


    public ArrayList<Movie> find(){
        ArrayList<Movie> list = new ArrayList<Movie>();
        boolean isInteger = false;
        try {
            //movie.txt 불러오기
            BufferedReader mvbr = new BufferedReader
                    (new InputStreamReader(new FileInputStream("./src/movie.txt"), "UTF-8"));

            //한줄씩 읽기
            String line;
            while ((line = mvbr.readLine()) != null) {
                String[] info = line.split(" ");
                for(int i=1 ; i<info.length; i++) {
                    String[] info2 = info[i].split("");
                    try{
                        Integer.parseInt(info2[0]);
                        isInteger = true;
                    }catch(NumberFormatException e) {
                        isInteger = false;
                    }

                    //첫 숫자가 나오기 전까지 영화제목
                    if(isInteger) {
                        String[] movienamelist = new String[i-1];
                        for(int j=1; j<i; j++) {
                            movienamelist[j-1] = info[j];
                        }

                        //movie 인자
                        String movietheater = info[0];
                        String moviename = String.join(" ", movienamelist);
                        String moviedate = info[i];
                        String moviestarttime = info[i+1];
                        String movieendtime = info[i+2];
                        String[] movierseat = new String[72];
                        String[] movieseat = new String[72];

                        //좌석 초기화
                        for(int j=0; j<12; j++) {
                            for(int k=0; k<6; k++) {
                                movierseat[j*6+k] = "0" + Integer.toString(k+1);
                            }
                        }

                        Arrays.fill(movieseat, "1");

                        //예약된 좌석 표기
                        int idx = 0;
                        for(int j=i+3; j<info.length; j++) {
                            String[] inforseat = info[j].split("");
                            int temp = (int)inforseat[0].charAt(0);
                            idx = 6*(temp - 65) + Integer.parseInt(inforseat[2]) - 1;
                            movierseat[idx] = "00";
                            movieseat[idx] = "0";
                        }

                        Movie movie = new Movie(movietheater, moviename, moviedate, moviestarttime, movieendtime, movierseat, movieseat);


                        /*
                         * System.out.printf("%s %s %s %s %s", movie.getTheater(), movie.getName(),
                         * movie.getDate(), movie.getStart(), movie.getEnd()); for(String st :
                         * movie.getRseat()) { System.out.printf(" %s", st); } System.out.println();
                         */


                        list.add(movie);
                        break;
                    }else
                        continue;
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        return list;

    }



}
