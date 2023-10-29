package movie;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MovieRepository {
	

    public void saveMovietxt(Movie movie, String seat) {
    	
    }

    public ArrayList<Movie> find(){
    	ArrayList<Movie> list = new ArrayList<Movie>();
    	boolean isInteger = false;
    	try {
    		BufferedReader mvbr = new BufferedReader
    				(new InputStreamReader(new FileInputStream("./src/movie.txt"), "UTF-8"));
    		
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
    				
    				if(isInteger) {
    					String[] movienamelist = new String[i-1];
    					for(int j=1; j<i; j++) {
    						movienamelist[j-1] = info[j];
    					}
    					String movietheater = info[0];
    					String moviename = String.join(" ", movienamelist);
    					String moviedate = info[i];
    					String moviestarttime = info[i+1];
    					String movieendtime = info[i+2];
    					String[] movierseat = new String[info.length - (i+3)];
    					int cnt = 0;
    					for(int j=i+3; j<info.length; j++) {
    						movierseat[cnt] = info[j];
    						cnt++;
    					}
    					
    					Movie movie = new Movie(movietheater, moviename, moviedate, moviestarttime, movieendtime, movierseat);
						
    					/* 구현 도중 확인 출력 문구인데 나중에 없애도 될까요
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

