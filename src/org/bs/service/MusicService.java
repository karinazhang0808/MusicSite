package org.bs.service;

import java.util.List;
import org.bs.model.Music;

public interface MusicService {
	public void add(Music music);

	public void delete(Music music);

	public void update(Music music);

	public Music findById(int id);

	public Music findByUserId(int id);

	public List<Music> search();
}