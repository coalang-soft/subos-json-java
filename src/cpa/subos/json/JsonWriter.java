package cpa.subos.json;

import java.io.IOException;
import java.io.OutputStream;

import com.google.gson.Gson;

import cpa.subos.datastore.WriteException;
import cpa.subos.datastore.Writer;
import cpa.subos.io.BufferIOBase;
import cpa.subos.io.IO;
import cpa.subos.io.IOBase;
import cpa.subos.io.OutputStreamIOBase;

public class JsonWriter implements Writer{

	private Gson gson;

	public JsonWriter(){
		this.gson = new Gson();
	}
	
	public BufferIOBase write(Object o) throws WriteException{
		try {
			return IO.string(gson.toJson(o));
		} catch (IOException e) {
			throw new WriteException(e);
		}
	}
	
	public <T extends IOBase<?>> T write(Object in, T out) throws WriteException{
		try {
			out.downloadFrom(write(in));
		} catch (IOException e) {
			throw new WriteException(e);
		} catch (WriteException e) {
			throw e;
		}
		return out;
	}
	
	public OutputStreamIOBase write(Object o, OutputStream s) throws WriteException{
		return write(o, IO.stream(s));
	}
	
}
