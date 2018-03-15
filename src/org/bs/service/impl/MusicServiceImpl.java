package org.bs.service.impl;

import java.util.List;
import org.bs.dao.MusicDao;
import org.bs.model.Music;
import org.bs.service.MusicService;

public class MusicServiceImpl implements MusicService {
	MusicDao musicDao;

	public void add(Music music) {
		musicDao.save(music);
	}

	public void delete(Music music) {
		musicDao.delete(music.getId());
	}

	public void update(Music music) {
		musicDao.update(music);
	}

	public Music findById(int id) {
		return musicDao.getById(id);
	}

	public Music findByUserId(int id) {
		return musicDao.getByUserId(id);
	}

	public List<Music> search() {
		return musicDao.query();
	}

	public void setMusicDao(MusicDao musicDao) {
		this.musicDao = musicDao;
	}
}