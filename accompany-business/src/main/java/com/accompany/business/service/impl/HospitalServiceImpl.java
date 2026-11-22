package com.accompany.business.service.impl;

import com.accompany.business.domain.entity.Hospital;
import com.accompany.business.mapper.HospitalMapper;
import com.accompany.business.service.IHospitalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医院 服务实现类
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital> implements IHospitalService {

}
