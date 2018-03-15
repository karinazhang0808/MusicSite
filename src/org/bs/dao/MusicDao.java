package org.bs.dao;

import java.util.List;
import org.bs.model.Music;

public interface MusicDao {
	public void save(Music music);

	public void delete(int id);

	public void update(Music music);

	public Music getById(int id);

	public List<Music> query();

	public Music getByUserId(int id);
}