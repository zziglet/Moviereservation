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
        String dummyContent = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;

            //좌석 내역 정보 전까지 movieInfo에 저장
            String movieInfo = String.format("%s %s %s %s %s",
                    movie.getTheater(),
                    movie.getName(),
                    movie.getDate(),
                    movie.getStart(),
                    movie.getEnd());

            //파일 끝까지 한 줄씩 읽기
            while ((currentLine = reader.readLine()) != null) {
                // 현재 읽은 줄에 해당 영화 정보가 있는지 확인
                if (currentLine.startsWith(movieInfo)) {
                    String[] seats = currentLine.split(" ");
                    dummyContent += String.join(" ", Arrays.copyOfRange(seats, 0, 5)) + " ";

                    // 취소된 좌석 삭제
                    for (int i = 5; i < seats.length; i++) {
                        if (!seats[i].equals(seat)) {
                            dummyContent += seats[i] + " ";
                        }
                    }
                    dummyContent = dummyContent.trim() + System.lineSeparator();
                } else {
                    dummyContent += currentLine + System.lineSeparator();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 변경된 내용 txt 파일에 저장
        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write(dummyContent);
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
					
					for(int i=info.length-1 ; i>0; i--) {
	                	if(Pattern.matches("^[\\d]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$",info[i])){
	                		idx = i;
	                		break;
	                	}
	                }
					
			for(int i=0; i<info.length; ) {

		                String[] movienamelist = new String[idx-1];
		                for(int j=1; j<idx; j++) {
		                     movienamelist[j-1] = info[j];
		                }

		                //movie 인자
		                String movietheater = info[0];
		                
		                String moviename = String.join(" ", movienamelist);
		                String moviedate = info[idx];
		                String moviestarttime = info[idx+1];
		                String movieendtime = info[idx+2];
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
		                int idx2 = 0;
		                for(int j=idx+3; j<info.length; j++) {
		                    String[] inforseat = info[j].split("");
		                    int temp = (int)inforseat[0].charAt(0);
		                    idx2 = 6*(temp - 65) + Integer.parseInt(inforseat[2]) - 1;
		                    movierseat[idx2] = "00";
		                    movieseat[idx2] = "0";
		                }

		                Movie movie = new Movie(movietheater, moviename, moviedate, moviestarttime, movieendtime, movierseat, movieseat);
						

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
