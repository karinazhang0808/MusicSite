package org.bs.service.impl;

import java.util.List;
import org.bs.dao.MemberlovesDao;
import org.bs.model.Memberloves;
import org.bs.service.MemberlovesService;

public class MemberlovesServiceImpl implements MemberlovesService {
	MemberlovesDao memberlovesDao;

	public void add(Memberloves memberloves) {
		memberlovesDao.save(memberloves);
	}

	public void delete(Memberloves memberloves) {
		memberlovesDao.delete(memberloves.getId());
	}

	public void update(Memberloves memberloves) {
		memberlovesDao.update(memberloves);
	}

	public Memberloves findById(int id) {
		return memberlovesDao.getById(id);
	}

	public Memberloves findByUserId(int id) {
		return memberlovesDao.getByUserId(id);
	}

	public List<Memberloves> search() {
		return memberlovesDao.query();
	}
	public List<Memberloves> search(String str) {
		return memberlovesDao.query(str);
	}

	public void setMemberlovesDao(MemberlovesDao memberlovesDao) {
		this.memberlovesDao = memberlovesDao;
	}
}