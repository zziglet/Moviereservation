package Movie;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MovieRepository {
    public void SaveMovietxt(Movie movie, String seat) {
        String srcdir = System.getProperty("user.dir") + "./src/";
        String path = srcdir + "movie.txt";
        File movieFile = new File(path);

        try (BufferedReader reader = new BufferedReader(new FileReader(movieFile))) {
            
            //좌석 내역 정보 전까지 movieInfo에 저장
            String movieInfo = String.format("%s %s %s %s %s",
                    movie.getTheater(),
                    movie.getName(),
                    movie.getDate(),
                    movie.getStart(),
                    movie.getEnd());
            
            //파일을 읽어서 리스트에 저장
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(movieInfo)) {
                    //해당 line의 마지막에 seat 추가
                    line = line + " " + seat;
                }
                lines.add(line);
            }
            reader.close();

            // 파일을 다시 쓰기
            BufferedWriter writer = new BufferedWriter(new FileWriter(movieFile));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void DeleteMovietxt(Movie movie, String seat) {
        File inputFile = new File("./src/movie.txt");
        StringBuilder updatedContent = new StringBuilder();


        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;

            //좌석 내역 정보 전까지 movieInfo에 저장
            String movieInfo = String.join(" ", movie.getKeys()[0], movie.getKeys()[1], movie.getKeys()[2], movie.getTheater(), movie.getName(), movie.getDate(), movie.getStart(), movie.getEnd());


            //파일 끝까지 한 줄씩 읽기
            while ((currentLine = reader.readLine()) != null) {
                // 현재 읽은 줄에 해당 영화 정보가 있는지 확인
                if (currentLine.startsWith(movieInfo)) {
                    String[] seats = currentLine.split(" ");
                    updatedContent.append(String.join(" ", Arrays.copyOfRange(seats, 0, 8))); // 기본 영화 정보

                    // 취소된 좌석 삭제
                    for (int i = 8; i < seats.length; i++) {
                        if (!seats[i].equals(seat)) {
                            updatedContent.append(" ").append(seats[i]);

                        }
                    }
                    updatedContent.append(System.lineSeparator());
                } else {
                    updatedContent.append(currentLine).append(System.lineSeparator());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 변경된 내용 txt 파일에 저장
        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write(updatedContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public ArrayList<Movie> find(){
        ArrayList<Movie> list = new ArrayList<Movie>();
        String userdir = System.getProperty("user.dir") + "./src/";
        String path = userdir +"movie.txt";
        int idx = 1;
        try {
            try (//movie.txt 불러오기
         BufferedReader mvbr = new BufferedReader
                    (new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            //한줄씩 읽기
            String line;
            while ((line = mvbr.readLine()) != null) {
               line = line.trim();
               String[] info = line.split("\\s+");
               
               for(int i=info.length-1 ; i>2; i--) {
                      if(Pattern.matches("^[\\d]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$",info[i])){
                         idx = i;
                         break;
                      }
                   }
               
         for(int i=0; i<info.length; ) {

                      String[] movienamelist = new String[idx-4];
                      for(int j=1; j<idx-3; j++) {
                           movienamelist[j-1] = info[j+3];
                      }
                      //movie 인자
                      String[] moviekey = new String[3];
                      for(int j=0; j<3; j++) {
                         moviekey[j] = info[j];
                      }
                      String movietheater = info[3];
                      ArrayList<String> theatersizelist = new ArrayList<String>();
                      String path1 = userdir +"theater.txt";
                      
                      try {
                          try (
                       BufferedReader mvbr2 = new BufferedReader
                                  (new InputStreamReader(new FileInputStream(path1), StandardCharsets.UTF_8))) {
                          
                          String line2;
                          while ((line2 = mvbr2.readLine()) != null) {
                             line2 = line2.trim();
                             String[] info2 = line2.split("\\s+");
                             if(info2[0].equals(moviekey[1])) {
                                theatersizelist.add(info2[2]);
                                    }
                                 }
                             }
                          
                       } catch (NumberFormatException e) {
                          // TODO Auto-generated catch block
                          e.printStackTrace();
                      } catch(IOException e) {
                          e.printStackTrace();
                      }
                      String moviename = String.join(" ", movienamelist);
                      String moviedate = info[idx];
                      String moviestarttime = info[idx+1];
                      String movieendtime = info[idx+2];

                      int allSeatsNum = Integer.parseInt(theatersizelist.get(i));
                      int colNum;
                      int lastColNum;
                      int rowNum;
                      if(allSeatsNum>=26*26){
                          rowNum = (allSeatsNum%26==0)?allSeatsNum/26:allSeatsNum/26+1;
                          colNum = 26;
                          lastColNum = (allSeatsNum%26!=0)?allSeatsNum%26:0;
                      }
                      else if(allSeatsNum>=19*19){
                          rowNum = (allSeatsNum%19==0)?allSeatsNum/19:allSeatsNum/19+1;
                          colNum  = 19;
                          lastColNum = (allSeatsNum%19!=0)?allSeatsNum%19:0;
                      }
                      else if(allSeatsNum >= 12*12){
                          rowNum = (allSeatsNum%12==0)?allSeatsNum/12:allSeatsNum/12+1;
                          colNum = 12;
                          lastColNum = (allSeatsNum%12!=0)?allSeatsNum%12:0;
                      }
                      else {
                          rowNum = (allSeatsNum%12==0)? allSeatsNum / 12 : allSeatsNum/12+1;
                          colNum = 12;
                          lastColNum = (allSeatsNum%12!=0)?allSeatsNum%12:0;
                      } 
                      
                      String[] movierseat = new String[Integer.parseInt(theatersizelist.get(i))];
                      String[] movieseat = new String[Integer.parseInt(theatersizelist.get(i))];
                      
                      //좌석 초기화
                      for(int j=0; j<rowNum; j++) {
                          
                    	 if((j == rowNum-1) && (lastColNum != 0)) {
                    		 for(int k=0; k<lastColNum; k++) {
                    			 String tmp = "";
	                             if(k < 9) {
	                                tmp = "0";
	                             }
	                             movierseat[j*colNum+k] = tmp + Integer.toString(k+1);
                    		 }
                    	 }else {
                    		 for(int k=0; k<colNum; k++) {
                    			 String tmp = "";
	                             if(k < 9) {
	                                tmp = "0";
	                             }
	                             movierseat[j*colNum+k] = tmp + Integer.toString(k+1);
                    		 }
                    	 }
                      }

                      Arrays.fill(movieseat, "1");

                      //예약된 좌석 표기
                      int idx2 = 0;
                      for(int j=idx+3; j<info.length; j++) {
                          String[] inforseat = info[j].split("");
                          int temp = (int)inforseat[0].charAt(0);
                          int seatNum = Integer.parseInt(inforseat[1] + inforseat[2]);
                          idx2 = colNum*(temp - 65) + seatNum - 1;
                          movierseat[idx2] = "00";
                          movieseat[idx2] = "0";
                      }
                      
                      Movie movie = new Movie(moviekey, movietheater, moviename, moviedate, moviestarttime, movieendtime, movierseat, movieseat);
                      list.add(movie);
                      break;
                      }
            }
         } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
            

        } catch(IOException e) {
            e.printStackTrace();
        }

        return list;

    }

}
