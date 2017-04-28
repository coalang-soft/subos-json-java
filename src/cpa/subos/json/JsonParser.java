package cpa.subos.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import cpa.subos.datastore.ParseException;
import cpa.subos.datastore.Parser;
import cpa.subos.io.IOBase;

public class JsonParser implements Parser{

	private Gson gson;

	public JsonParser(){
		this.gson = new Gson();
	}
	
	public <T> T parse(Class<T> c, InputStream s){
		return gson.fromJson(new InputStreamReader(s), c);
	}
	
	public <T> T parse(Class<T> c, IOBase<?> b) throws ParseException{
		try {
			return parse(c,b.reader());
		} catch (IOException e) {
			throw new ParseException(e);
		}
	}
	
}
