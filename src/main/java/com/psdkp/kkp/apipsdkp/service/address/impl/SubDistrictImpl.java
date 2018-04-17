package com.psdkp.kkp.apipsdkp.service.address.impl;

import com.psdkp.kkp.apipsdkp.domain.address.SubDistrict;
import com.psdkp.kkp.apipsdkp.repository.address.SubDistrictDao;
import com.psdkp.kkp.apipsdkp.service.BaseService;
import com.psdkp.kkp.apipsdkp.service.address.SubDistrictService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubDistrictImpl implements SubDistrictService {

    @Autowired
    private SubDistrictDao subDistrictDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(subDistrictDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(SubDistrict subDistrict) {
        if (subDistrict.getName().equals("") || subDistrict.getCode().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            SubDistrict p = subDistrictDao.findByCode(subDistrict.getCode());
            if (p != null) {
                return responMessage.DUPLICATE("KODE");
            } else {
                SubDistrict p2 = subDistrictDao.findByName(subDistrict.getName());
                if (p2 != null) {
                    return responMessage.DUPLICATE("NAMA");
                } else {
                    subDistrictDao.save(subDistrict);
                    return responMessage.SUCCESS_PROCESS_DATA();
                }
            }
        }
    }

    @Override
    public Object edit(SubDistrict subDistrict) {
        if (subDistrict.getId() == null || subDistrict.getName().equals("") || subDistrict.getCode().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            SubDistrict pCode = subDistrictDao.findId(subDistrict.getId());
            if (pCode != null) {
                if (subDistrict.getCode().equals(pCode.getCode())) {
                    SubDistrict p2 = subDistrictDao.findByName(subDistrict.getName());
                    if (p2 != null) {
                        return responMessage.DUPLICATE("NAMA");
                    } else {
                        subDistrictDao.save(subDistrict);
                        return responMessage.SUCCESS_PROCESS_DATA();
                    }
                } else {
                    SubDistrict proCode = subDistrictDao.findByCode(subDistrict.getCode());
                    if (proCode != null) {
                        return responMessage.DUPLICATE("CODE");
                    } else {
                        if (!subDistrict.getName().equals(pCode.getName())) {
                            SubDistrict p2 = subDistrictDao.findByName(subDistrict.getName());
                            if (p2 != null) {
                                return responMessage.DUPLICATE("NAMA");
                            } else {
                                subDistrictDao.save(subDistrict);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            }
                        } else {
                            subDistrictDao.save(subDistrict);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        }
                    }
                }
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object del(Integer id) {
        if (id == null) {
            return responMessage.BAD_REUQEST();
        } else {
            SubDistrict pCode = subDistrictDao.findId(id);
            if (pCode != null) {
                subDistrictDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        SubDistrict pCode = subDistrictDao.findId(id);
        if (pCode != null) {
            return responMessage.SUCCESS_GET(subDistrictDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}