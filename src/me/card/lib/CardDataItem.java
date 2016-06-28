package me.card.lib;

import java.util.List;

public class CardDataItem {
	private int type;
	private int id;
	private String ga_prefix;
	private String title;
	private List<String> images;

	public void setType(int type) {
		this.type = type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setGa_prefix(String ga_prefix) {
		this.ga_prefix = ga_prefix;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public int getType() {
		return type;
	}

	public int getId() {
		return id;
	}

	public String getGa_prefix() {
		return ga_prefix;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getImages() {
		return images;
	}
}