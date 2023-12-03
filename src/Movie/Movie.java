package Movie;


public class Movie {


	private String keys[];
	private String theater;
	private String name;
    private String date;
    private String start;
    private String end;
    private String rseat[];
    private String seat[];
    
    Movie(){};

    public Movie(String keys[], String theater, String name, String date, String start, String end, String[] rseat, String[] seat) {
		super();
		this.keys = keys;
		this.theater = theater;
		this.name = name;
		this.date = date;
		this.start = start;
		this.end = end;
		this.rseat = rseat;
		this.seat = seat;

	}
    

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
    
    public String[] getRseat() {
		return rseat;
	}

	public void setRseat(String[] rseat) {
		this.rseat = rseat;
	}

	public String[] getSeat() {
		return seat;
	}

	public void setSeat(String[] seat) {
		this.seat = seat;
	}

}

