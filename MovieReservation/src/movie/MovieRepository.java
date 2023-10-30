package movie;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieRepository {
	

    public void saveMovietxt(Movie movie, String seat) {
    	
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
    
    public void DeleteMovietxt(Movie movie, String seat) {
    	
    }

}
