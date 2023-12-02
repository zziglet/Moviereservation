package Admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class AdminService {
	 private static String theaternumMax = "2600"; 
	 private static String theaternumMin = "1"; 
	 
	 private static String movieruntimeMax = "1440"; 
	 private static String movieruntimeMin = "1"; 
	 
	 
	 public void AddTheater(){
		String lastKey = null;
		String userdir = System.getProperty("user.dir") + "./src/";
     	String path = userdir +"theater.txt";
     	ArrayList<String> theaterNamelist = new ArrayList<String>();
     	ArrayList<String> theaterKeylist = new ArrayList<String>();
     	String num = null;
     
     	try {
    	 try (BufferedReader mvbr = new BufferedReader
                (new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
             String line;
             while ((line = mvbr.readLine()) != null) {
                 line = line.trim();
                 String[] info = line.split(" ");
                 theaterKeylist.add(info[0]);
                 theaterNamelist.add(info[1]);
             }
             
             String[] info2 = theaterKeylist.get(theaterKeylist.size()-1).split("");
             String[] info3 = new String[info2.length-1];
             for(int j=1; j<info2.length; j++) {
                  info3[j-1] = info2[j];
             }
             lastKey = String.join("", info3);
        	 }
        	 
             System.out.println("추가할 상영관 이름을 입력하십시오.\n");
               
             @SuppressWarnings("resource")
     		 Scanner scan = new Scanner(System.in);
             boolean flag1 = false; // 관 번호 체크
             lp1:
             while (!flag1) {
            	System.out.print("MovieReservation >> ");
                String input = scan.nextLine();
                if (!Pattern.matches("^[0-99]+$", input)) {
                   System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.\n");
                   continue lp1;
                }
                
                for(String st : theaterNamelist) {
                   if(st.equals(input)) {
                      System.out.println("..! 오류 : 같은 상영관 번호가 있습니다. 다시 입력해주세요.\n");
                       continue lp1;
                   }
                }
                
                System.out.println("추가할 상영관의 좌석 수를 입력하십시오.\n");
                boolean flag2 = false;
                lp2:
                while(!flag2) {
		             flag1 = true;
		 	          
		 	         System.out.print("MovieReservation >> ");
		 	         scan = new Scanner(System.in);
		 	         num = scan.nextLine();
		 	          
		 	         if (!Pattern.matches("^[0-9]*$", num)) {
		                 System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.\n");
		                 continue lp2;
		             }
		 	         
		 	         if((Integer.parseInt(num) < Integer.parseInt(theaternumMin)) || (Integer.parseInt(num) > Integer.parseInt(theaternumMax))) {
		                System.out.println("..! 오류 : 가능한 좌석 수가 아닙니다. 다시 입력해주세요.\n");
		                continue lp2;
		             }
		 	        
		             flag2 = true;
                }
		          
	            File file = new File(path);
	            if (!file.exists()) {
	                System.out.println("Cannot find file");
	                return;
	            }
	            FileWriter fw = new FileWriter(file, true);
	            BufferedWriter writer = new BufferedWriter(fw);
	            String str = "T" + Integer.toString(Integer.parseInt(lastKey) + 1) + " " + input + " " + num;
	            writer.write(str + System.lineSeparator());
	            writer.close();
             }
            
         } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); 
         } catch(IOException e) {
            e.printStackTrace();
         }
    
    
    }
	    
	public void AddMovieInfo(){
		String lastKey = null;
		String userdir = System.getProperty("user.dir") + "./src/";
     	String path = userdir +"movieinfo.txt";
     	ArrayList<String> movieNamelist = new ArrayList<String>();
     	ArrayList<String> movieKeylist = new ArrayList<String>();
     	ArrayList<String> movieRuntimelist = new ArrayList<String>();
     	String runtime = null;
     
     	try {
    	 try (BufferedReader mvbr = new BufferedReader
                (new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
             String line;
             while ((line = mvbr.readLine()) != null) {
                 line = line.trim();
                 String[] info = line.split("\\s+");
                 movieKeylist.add(info[0]);
                 ArrayList<String> namebuflist = new ArrayList<String>();
                 for(int i=1 ; i<info.length - 1; i++) {
	                namebuflist.add(info[i]);
	             }
                 String moviename = String.join(" ", namebuflist);
                 
                 movieNamelist.add(moviename);
                 movieRuntimelist.add(info[info.length-1]);
             }
             
             String[] info2 = movieKeylist.get(movieKeylist.size()-1).split("");
             String[] info3 = new String[info2.length-1];
             for(int j=1; j<info2.length; j++) {
                  info3[j-1] = info2[j];
             }
             lastKey = String.join("", info3);
        	 }
        	 
             System.out.println("추가할 영화의 제목을 입력하십시오.\n");
               
             @SuppressWarnings("resource")
     		 Scanner scan = new Scanner(System.in);
             boolean flag1 = false; // 영화 제목 체크
             lp1:
             while (!flag1) {
            	System.out.print("MovieReservation >> ");
                String input = scan.nextLine().trim();
                System.out.println(input);
                if (!Pattern.matches("^[가-힣a-zA-Z0-9 ,:.!?()~-]+$", input)) {
                   System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.\n");
                   continue lp1;
                }
                
                
                for(String st : movieNamelist) {
                   if(st.equals(input)) {
                      System.out.println("..! 오류 : 같은 영화 제목이 있습니다. 다시 입력해주세요.\n");
                       continue lp1;
                   }
                }
                
                System.out.println("추가할 영화의 러닝타임을 입력하십시오.\n");
                boolean flag2 = false;
                lp2:
                while(!flag2) {
		             flag1 = true;
		 	          
		 	         System.out.print("MovieReservation >> ");
		 	         scan = new Scanner(System.in);
		 	         runtime = scan.nextLine();
		 	          
		 	         if (!Pattern.matches("^[0-9]*$", runtime)) {
		                 System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.\n");
		                 continue lp2;
		             }
		 	         
		 	         if((Integer.parseInt(runtime) < Integer.parseInt(movieruntimeMin)) || (Integer.parseInt(runtime) > Integer.parseInt(movieruntimeMax))) {
		                System.out.println("..! 오류 : 가능한 러닝타임이 아닙니다. 다시 입력해주세요.\n");
		                continue lp2;
		             }
		 	        
		             flag2 = true;
                }
		          
	            File file = new File(path);
	            if (!file.exists()) {
	                System.out.println("Cannot find file");
	                return;
	            }
	            FileWriter fw = new FileWriter(file, true);
	            BufferedWriter writer = new BufferedWriter(fw);
	            String str = "O" + Integer.toString(Integer.parseInt(lastKey) + 1) + " " + input + " " + runtime;
	            writer.write(str + System.lineSeparator());
	            writer.close();
             }
            
         } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); 
         } catch(IOException e) {
            e.printStackTrace();
         }
        
              
	}
		
	public void AddMovie(){
		String lastKey = null;
		String userdir = System.getProperty("user.dir") + "./src/";
     	String path = userdir +"theater.txt";
     	ArrayList<String> theaterNamelist = new ArrayList<String>();
     	ArrayList<String> theaterKeylist = new ArrayList<String>();
     	
     	
     	System.out.println("\n추가할 상영스케줄의 상영관 key를 입력하십시오.\n");
     	Scanner scan = new Scanner(System.in);
        boolean flag1 = false; // 상영관 체크
        lp1:
        while (!flag1) {
	     	try {
	    	 try (BufferedReader mvbr = new BufferedReader
	                (new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
	             String line;
	             while ((line = mvbr.readLine()) != null) {
	            	 System.out.println(line);
	                 line = line.trim();
	                 String[] info = line.split(" ");
	                 theaterKeylist.add(info[0]);
	                 theaterNamelist.add(info[1]);
	             }
	             
	             String[] info2 = theaterKeylist.get(theaterKeylist.size()-1).split("");
	             String[] info3 = new String[info2.length-1];
	             for(int j=1; j<info2.length; j++) {
	                  info3[j-1] = info2[j];
	             }
	             lastKey = String.join("", info3);
	    	 	}
	            
	         } catch (NumberFormatException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace(); 
	         } catch(IOException e) {
	            e.printStackTrace();}
	     	System.out.println();
	     	System.out.print("MovieReservation >> ");
            String input = scan.nextLine().trim();
	     	
	     	if (!Pattern.matches("^[A-Z][0-9]*$", input)) {
                System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.\n");
                continue lp1;}
             
             
            if((Integer.parseInt(input.substring(1)) > Integer.parseInt(lastKey)) || (Integer.parseInt(input.substring(1)) == 0)){
                System.out.println("..! 오류 : 벗어난 Key 값입니다. 다시 입력해주세요.\n");
                continue lp1;}
            
             System.out.println("\n추가할 상영스케줄의 영화 정보 key를 입력하십시오.\n");
             boolean flag2 = false;
             lp2:
             while(!flag2) {
		             flag1 = true;
		 	          
		     		 lastKey = null;
		         	 path = userdir +"movieinfo.txt";
		         	 ArrayList<String> movieNamelist = new ArrayList<String>();
		         	 ArrayList<String> movieKeylist = new ArrayList<String>();
		         	 ArrayList<String> movieRuntimelist = new ArrayList<String>();
		         	 String runtime = null;
		         
		         	 try {
		        	  try (BufferedReader mvbr = new BufferedReader
		                    (new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
		                 String line;
		                 while ((line = mvbr.readLine()) != null) {
		                	 System.out.println(line);
		                     line = line.trim();
		                     String[] info = line.split("\\s+");
		                     movieKeylist.add(info[0]);
		                     ArrayList<String> namebuflist = new ArrayList<String>();
		                     for(int i=1 ; i<info.length - 1; i++) {
		    	                namebuflist.add(info[i]);
		    	             }
		                     String moviename = String.join(" ", namebuflist);
		                     
		                     movieNamelist.add(moviename);
		                     movieRuntimelist.add(info[info.length-1]);
		                 }
		                 
		                 String[] info2 = movieKeylist.get(movieKeylist.size()-1).split("");
		                 String[] info3 = new String[info2.length-1];
		                 for(int j=1; j<info2.length; j++) {
		                      info3[j-1] = info2[j];
		                 }
		                 lastKey = String.join("", info3);
		            	 }
		            	
		                
		             } catch (NumberFormatException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace(); 
		             } catch(IOException e) {
		                e.printStackTrace();
		             }
		         	System.out.println();
		         	System.out.print("MovieReservation >> ");
		            input = scan.nextLine().trim();
			     	
			     	if (!Pattern.matches("^[A-Z][0-9]*$", input)) {
		                System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.\n");
		                continue lp2;}
		             
		             
		            if((Integer.parseInt(input.substring(1)) > Integer.parseInt(lastKey)) || (Integer.parseInt(input.substring(1)) == 0)){
		                System.out.println("..! 오류 : 벗어난 Key 값입니다. 다시 입력해주세요.\n");
		                continue lp2;}
		            
		            System.out.println("추가할 상영스케줄의 상영 날짜를 입력하십시오.\n");
		            boolean flag3 = false;
		            lp3:
		            while(!flag3) {
				             flag2 = true;
				             //선택한 러닝타임 저장
				             String runtimebuf = new String();
				             
				             for(int j=0; j<movieKeylist.size(); j++) {
				            	 if(movieKeylist.get(j).equals(input)) {
				            		 runtimebuf = movieRuntimelist.get(j);
				            	 }
				             }
				             System.out.println();
				             System.out.print("MovieReservation >> ");
					         input = scan.nextLine().trim();
					         
				             if (!Pattern.matches("^[\\d]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", input)) {
				                System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.\n");
				                continue lp3;}
				             
				             System.out.println("\n추가할 상영스케줄의 상영 시작 시간을 입력하십시오.\n");
				             boolean flag4 = false;
				             lp4:
				             while(!flag4) {
				            	 flag3 = true;
					             System.out.print("MovieReservation >> ");
						         input = scan.nextLine().trim();
						         
						         if (!Pattern.matches("^([1-9]|[01][0-9]|2[0-3]):([0-5][0-9])$", input)) {
					             	System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.\n");
					                continue lp4;}
						         
						         //시작시간 입력 + 러닝타임 -> 종료 시간 게산
						         int runTime = Integer.parseInt(runtimebuf);
						         int runTimeHour = runTime / 60;
						         int runTimeMin = runTime % 60;
						         
						         int startHour = Integer.parseInt(input.substring(0, 2));
						         int startMin = Integer.parseInt(input.substring(3));
						         
						         if((startMin + runTimeMin) >= 60) {
						        	 startHour += (startMin + runTimeMin) / 60;
						        	 startMin += (startMin + runTimeMin) % 60;
						        	 startMin -= 60;
						         }
						         
						         int endHour = startHour + runTimeHour;
						         int endMin = startMin + runTimeMin;
						         
						         String cnt = "";
						         if(endMin == 0) {
						        	 cnt = Integer.toString(endMin) + "0";
						         }else if(endMin / 10 == 0){
						        	 cnt = "0" + Integer.toString(endMin);
						         }else
						        	 cnt = Integer.toString(endMin);
						        	 
						         String endtime = Integer.toString(endHour) + ":" + cnt;

						         
						         if (endHour >= 24) {
						             	System.out.println("..! 오류 : 상영 시작시간이 너무 늦습니다. 다시 입력해주세요.\n");
						             	continue lp4;}
						         
						         System.out.println("시작 시간: " + input + "\n");
						         System.out.println("종료 시간: " + endtime + "\n");
						         
						         //상영스케줄 내에 겹치는 시간 있는지 검사
				             }
		            }
             }
    
        }
     
	}

    public void EditTheaterNum() {
        /* 관번호 수정 */
        try {
            // 1.
            // 관리자에게 수정할 관 번호 선택
            System.out.println("수정할 관 번호가 담긴 리스트입니다. \n");
            String userdir = System.getProperty("user.dir") + "./src/user/";
            String srcdir = System.getProperty("user.dir") + "./src/";
            String path = srcdir + "theater.txt";
            BufferedReader membr = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            StringBuffer inputBuffer = new StringBuffer();
            ArrayList<String> movieList = new ArrayList<>();
            String line;
            while ((line = membr.readLine()) != null) {
                movieList.add(line);
            }
            membr.close();
            for (int i = 0; i < movieList.size(); i++) {
                System.out.println((i + 1) + ". " + movieList.get(i));
            }
            System.out.println();
            System.out.println("수정할 리스트의 번호를 입력하십시오.");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            // 오류 처리 해줘야함
            int result = Integer.parseInt(input.replaceAll("\\s+", ""));
            System.out.println("수정할 관 번호를 입력하십시오.");
            input = scan.nextLine();
            // 오류 처리 해줘야함
            String repString = input.replaceAll("\\s+", "");
            String[] info = movieList.get(result - 1).split(" ");
            String theaterKey = info[0];
            // theater.txt에 수정된 내역 적용
            path = srcdir + "theater.txt";
            membr = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            while ((line = membr.readLine()) != null) {
                info = line.split(" ");
                if (info[0].equals(theaterKey))
                    info[1] = repString;
                line = "";
                for (String str : info) {
                    line += str + " ";
                }
                inputBuffer.append(line);
                inputBuffer.append("\n");
            }
            membr.close();
            FileOutputStream fileout = new FileOutputStream(path);
            fileout.write(inputBuffer.toString().getBytes());
            fileout.close();
            scan.close();
            /* movie.txt (상영 스케줄)에 수정된 내역 적용 */
            path = srcdir + "movie.txt";
            membr = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            String movieKey = "";
            while ((line = membr.readLine()) != null) {
                info = line.split(" ");
                if (info[1].equals(theaterKey))
                    movieKey = info[0];
                info[3] = repString;
                line = "";
                for (String str : info) {
                    line += str + " ";
                }
                inputBuffer.append(line);
                inputBuffer.append("\n");
            }
            membr.close();
            fileout = new FileOutputStream(path);
            fileout.write(inputBuffer.toString().getBytes());
            fileout.close();
            scan.close();
            // 예매 내역 반영
            File folder = new File(userdir);
            File[] filelist = folder.listFiles();
            if (filelist != null) {
                for (File file : filelist) {
                    if (file.isFile() && file.canRead()) {
                        FileReader filereader = new FileReader(file);
                        BufferedReader bufReader = new BufferedReader(filereader);
                        while ((line = bufReader.readLine()) != null) {
                            info = line.split(" ");
                            if (info[0].equals(movieKey))
                                info[3] = repString;
                            line = "";
                            for (String str : info) {
                                line += str + " ";
                            }
                            inputBuffer.append(line);
                            inputBuffer.append("\n");
                        }
                        bufReader.close();
                        fileout = new FileOutputStream(path);
                        fileout.write(inputBuffer.toString().getBytes());
                        fileout.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void EditTheaterSeat() {
        /* 전체 좌석 수 변경 -> 예매 내역 제외 후 반영하기 */
        String userdir = System.getProperty("user.dir") + "./src/user/";
        String srcdir = System.getProperty("user.dir") + "./src/";
        try {
            /* 1. 제외시킬 내역을 배열에 담기 */
            File folder = new File(userdir);
            File[] filelist = folder.listFiles();
            ArrayList<String> tkeyinIDList = new ArrayList<>();
            String[] info;
            String line;
            // id.txt에서 예매가 된 내역의 Theater 키값 추출
            if (filelist != null) {
                for (File file : filelist) {
                    if (file.isFile() && file.canRead()) {
                        FileReader filereader = new FileReader(file);
                        BufferedReader bufReader = new BufferedReader(filereader);
                        // id.txt 속의 예매된 theater 키값 추출
                        info = null;
                        while ((line = bufReader.readLine()) != null) {
                            info = line.split(" ");
                            if (info[0].matches("^[M-Z][\\d]+$")) {
                                tkeyinIDList.add(info[1]);
                            }
                        }
                        bufReader.close();
                    }
                }
            }
            // 중복제거
            for (String item : tkeyinIDList) {
                if (!tkeyinIDList.contains(item))
                    tkeyinIDList.add(item);
            }
            /* 2. theater.txt에 저장되어있는 키값 추출 */
            String path = srcdir + "theater.txt";
            BufferedReader bufReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            ArrayList<String> theaterList = new ArrayList<>();
            while ((line = bufReader.readLine()) != null) {
                theaterList.add(line);
            }
            bufReader.close();
            /* 3. 각 값을 비교해 같으면 제외(삭제) */
            info = null;
            for (String str : theaterList) {
                info = str.split(" ");
                if (tkeyinIDList.contains(info[0])) {
                    theaterList.remove(str);
                }
            }
            /* 4. 제외된 내역 출력 */
            System.out.println("수정할 좌석 수가 담긴 리스트입니다. \n");
            for (int i = 0; i < theaterList.size(); i++) {
                System.out.println((i + 1) + ": " + theaterList.get(i));
            }
            System.out.println("수정할 리스트의 번호를 입력하십시오.");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            // 오류 처리 해줘야함
            int result = Integer.parseInt(input.replaceAll("\\s+", ""));
            
            System.out.println("수정할 좌석 수를 입력하십시오.");
            input = scan.nextLine();
            // 오류 처리 해줘야함
            String seatsNum = input.replaceAll("\\s+", "");
            info = theaterList.get(result - 1).split(" ");
            String theaterKey = info[0];
            // theater.txt에 수정된 내역 적용
            
            path = srcdir + "theater.txt";
            bufReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            StringBuffer inputBuffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
                info = line.split(" ");
                if (info[0].equals(theaterKey))
                    info[2] = seatsNum;
                line = "";
                for (String str : info) {
                    line += str + " ";
                }
                inputBuffer.append(line);
                inputBuffer.append("\n");
            }
            bufReader.close();
            FileOutputStream fileout = new FileOutputStream(path);
            fileout.write(inputBuffer.toString().getBytes());
            fileout.close();
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void EditTheaterInfoName(String keyString, String moviename) {
        try {
            /* 영화 제목 수정 -> 예매 내역 반영, 상영 스케줄 반영 */
            // 1.
            // 관리자에게 수정할 제목 리스트 제공
            System.out.println("수정할 제목이 담긴 리스트입니다. \n");
            String userdir = System.getProperty("user.dir") + "./src/user/";
            String srcdir = System.getProperty("user.dir") + "./src/";
            String path = srcdir + "movieinfo.txt";
            BufferedReader membr = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            StringBuffer inputBuffer = new StringBuffer();
            ArrayList<String> movieList = new ArrayList<>();
            String line;
            while ((line = membr.readLine()) != null) {
                movieList.add(line);
            }
            membr.close();
            for (int i = 0; i < movieList.size(); i++) {
                System.out.println((i + 1) + ". " + movieList.get(i));
            }
            System.out.println();
            System.out.println("수정할 리스트의 번호를 입력하십시오.");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            // 오류 처리 해줘야함
            int result = Integer.parseInt(input.replaceAll("\\s+", ""));
            System.out.println("수정할 제목을 입력하십시오.");
            input = scan.nextLine();
            scan.close();
            // 오류 처리 해줘야함
            String repString = input.replaceAll("\\s+", "");
            String[] info = movieList.get(result - 1).split(" ");
            String movieinfoKey = info[0];
            /// movieinfo.txt 에 수정된 내역 적용
            path = srcdir + "movieinfo.txt";
            membr = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            while ((line = membr.readLine()) != null) {
                info = line.split(" ");
                if (info[0].equals(movieinfoKey))
                    info[1] = repString;
                ;
                line = "";
                for (String str : info) {
                    line += str + " ";
                }
                inputBuffer.append(line);
                inputBuffer.append("\n");
            }
            membr.close();
            FileOutputStream fileout = new FileOutputStream(path);
            fileout.write(inputBuffer.toString().getBytes());
            fileout.close();
            //// movie.txt (상영 스케줄)에 수정된 내역 적용
            path = srcdir + "movie.txt";
            membr = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            String movieKey = "";
            while ((line = membr.readLine()) != null) {
                info = line.split(" ");
                if (info[2].equals(movieinfoKey))
                    info[4] = repString;
                line = "";
                for (String str : info) {
                    line += str + " ";
                }
                inputBuffer.append(line);
                inputBuffer.append("\n");
            }
            membr.close();
            fileout = new FileOutputStream(path);
            fileout.write(inputBuffer.toString().getBytes());
            fileout.close();
           // 예매 내역 반영
            File folder = new File(userdir);
            File[] filelist = folder.listFiles();
            if (filelist != null) {
                for (File file : filelist) {
                    if (file.isFile() && file.canRead()) {
                        FileReader filereader = new FileReader(file);
                        BufferedReader bufReader = new BufferedReader(filereader);
                        while ((line = bufReader.readLine()) != null) {
                            info = line.split(" ");
                            if (info[0].equals(movieKey))
                                info[4] = repString;
                            line = "";
                            for (String str : info) {
                                line += str + " ";
                            }
                            inputBuffer.append(line);
                            inputBuffer.append("\n");
                        }
                        bufReader.close();
                        fileout = new FileOutputStream(path);
                        fileout.write(inputBuffer.toString().getBytes());
                        fileout.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void EditTheaterRuntime() {
        /* 러닝 타임 수정 -> 상영스케줄에 등록 내역 제외 후 상영스케줄 반영 */
        /* 1. 제외시킬 내역을 배열에 담기 */
        String srcdir = System.getProperty("user.dir") + "./src/";
        try {
            String path = srcdir + "movie.txt";
            BufferedReader bufReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            // 상영스케줄에 존재하는 영화 키값 추출
            String[] info = null;
            String line;
            ArrayList<String> infokeyinmovieList = new ArrayList<>();
            while ((line = bufReader.readLine()) != null) {
                info = line.split(" ");
                if (info[0].matches("^[M-Z][\\d]+$")) {
                    infokeyinmovieList.add(info[2]);
                }
            }
            bufReader.close();
            // 중복제거
            for (String item : infokeyinmovieList) {
                if (!infokeyinmovieList.contains(item))
                    infokeyinmovieList.add(item);
            }
            /* 2. movieinfo.txt에 저장되어있는 키값 추출 */
            path = srcdir + "movieinfo.txt";
            bufReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            ArrayList<String> runningList = new ArrayList<>();
            while ((line = bufReader.readLine()) != null) {
                runningList.add(line);
            }
            bufReader.close();
            /* 3. 각 값을 비교해 같으면 제외(삭제) */
            info = null;
            for (String str : runningList) {
                info = str.split(" ");
                if (infokeyinmovieList.contains(info[0])) {
                    runningList.remove(str);
                }
            }
            /* 4. 제외된 내역 출력 */
            System.out.println("수정할 좌석 수가 담긴 리스트입니다. \n");
            for (int i = 0; i < runningList.size(); i++) {
                System.out.println((i + 1) + ": " + runningList.get(i));
            }
            System.out.println("수정할 리스트의 번호를 입력하십시오.");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            // 오류 처리 해줘야함
            int result = Integer.parseInt(input.replaceAll("\\s+", ""));
            System.out.println("수정할 러닝타임 기간을 입력하십시오.");
            input = scan.nextLine();
            // 오류 처리 해줘야함
            String runningTime = input.replaceAll("\\s+", "");
            info = runningList.get(result - 1).split(" ");
            String movieinfoKey = info[0];
            // movieinfo.txt에 수정된 내역 적용
            path = srcdir + "moiveinfo.txt";
            bufReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            StringBuffer inputBuffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
                info = line.split(" ");
                if (info[0].equals(movieinfoKey))
                    info[2] = runningTime;
                line = "";
                for (String str : info) {
                    line += str + " ";
                }
                inputBuffer.append(line);
                inputBuffer.append("\n");
            }
            bufReader.close();
            FileOutputStream fileout = new FileOutputStream(path);
            fileout.write(inputBuffer.toString().getBytes());
            fileout.close();
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DeleteTheater() {
        String userdir = System.getProperty("user.dir") + "./src/";
        String filePath = userdir +"movie.txt";
        String[] cantDeleteTheater = null; //상영스케쥴이 있어 삭제할 수 없는 상영관

        try {
            // 파일에서 문자열 읽어오기
            Set<String> secondValuesSet = new HashSet<>();

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String movieInfoLine;

                // 파일에서 각 줄을 읽어와 두 번째 값을 Set에 추가
                while ((movieInfoLine = br.readLine()) != null) {
                    String[] tokens = movieInfoLine.split("\\s+"); // 공백을 기준으로 문자열을 나눔
                    if (tokens.length >= 2) {
                        secondValuesSet.add(tokens[1]);
                    }
                }
            }
            // Set을 배열로 변환
            cantDeleteTheater = secondValuesSet.toArray(new String[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //삭제 가능 상영관의 배열 만들기
        ArrayList<String> theaterInfo = new ArrayList<>();
        String[] theaterArray = null; //전체 상영관 정보, 원본 theater 파일 정보
        String[] canTheaterArray = null; //삭제 가능 상영관 정보
        System.out.println("[상영관 리스트]");
        userdir = System.getProperty("user.dir") + "./src/";
        filePath = userdir +"theater.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // 한 줄씩 읽어오기
            while ((line = br.readLine()) != null) {
                // 각 라인을 분리하여 배열로 저장
                theaterInfo.add(line);
            }
            // List를 배열로 변환
            theaterArray = new String[theaterInfo.size()];
            theaterArray = theaterInfo.toArray(theaterArray);

            //상영스케쥴이 있는 상영관은 삭제 가능 상영관 배열에서 삭제
            ArrayList<String> result = new ArrayList<>();

            outerLoop:
            for (String strA : theaterArray) {
                for (String strB : cantDeleteTheater) {
                    if (strA.startsWith(strB)) {
                        // 배열 cantDeleteTheater의 원소로 시작하는 경우, 현재의 strA를 무시하고 다음으로 넘어감
                        continue outerLoop;
                    }
                }
                // 배열 cantDeleteTheater의 어떠한 원소로도 시작하지 않는 경우, result에 추가
                result.add(strA);
            }
            canTheaterArray = result.toArray(new String[0]);

                // 배열의 각 요소 출력
                for (String info : canTheaterArray) {
                    System.out.println(info);
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //삭제할 상영관의 키 값 사용자에게 입력 받음
        String deleteTheater = null; //삭제할 상영관 정보
        String keyDeleteTheater = null; //삭제할 상영관 키값
        while (true){
            System.out.print("\n삭제할 상영관의 키 값을 입력하세요: ");
            Scanner scanner = new Scanner(System.in);
            keyDeleteTheater = scanner.nextLine();

            // 입력 형식 검사
            if (!keyDeleteTheater.matches("^T\\d+$")) {
                System.out.println("입력 형식이 잘못 되었습니다.\n");
                continue;
            }

            // theaterInfo 배열에서 검색
            for (String theater : canTheaterArray) {
                if (theater.startsWith(keyDeleteTheater)) {
                    deleteTheater = theater;
                }
            }
            if(deleteTheater == null){
                System.out.println("해당 키 값을 가진 상영관은 없습니다. 다시 입력해 주세요.\n");
                continue;
            }
            String input = null;

            do {
                // 사용자로부터 입력 받기
                System.out.println("\n선택된 상영관 : "+deleteTheater);
                System.out.print("해당 상영관을 삭제하시겠습니까? (y/n): ");

                input = scanner.nextLine().replaceAll("\\s+", "");;
                // 입력 값에 따라 분기
                if ("y".equalsIgnoreCase(input)) {
                    // deleteTheater를 삭제한 전체 상영관 정보 ArrayList 생성
                    ArrayList<String> modifiedList = new ArrayList<>();
                    for (String theater : theaterArray) {
                        if (!(deleteTheater.equals(theater))) {
                            modifiedList.add(theater);
                        }
                    }

                    // ArrayList를 배열로 변환
                    String[] modifiedArray = modifiedList.toArray(new String[0]);
                    //상영관 파일에 덮어쓰기
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                        for (String line : modifiedArray) {
                            writer.write(line);
                            writer.newLine(); // 각 원소를 새로운 줄에 작성
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(deleteTheater + " 이 삭제 되었습니다.\n");
                    return;

                } else if ("n".equalsIgnoreCase(input)) {
                    System.out.println("메인화면으로 돌아갑니다.\n");
                    return;
                } else {
                    System.out.println("잘못된 입력입니다.");
                }

            } while (!"y".equalsIgnoreCase(input) && !"n".equalsIgnoreCase(input));

            scanner.close();
        }
    }

    public void DeleteMovieInfo() {
    }

    public void DeleteMovie() {
    }
}
