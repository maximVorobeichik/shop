package ru.home.shopmx.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.shopmx.dao.ManufacturerDao;
import ru.home.shopmx.entity.Manufacturer;
import ru.home.shopmx.web.dto.ManufacturerDto;
import ru.home.shopmx.web.dto.mapper.ManufacturerMapper;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManufacturerService {

    private final ManufacturerDao manufacturerDao;
    private final ManufacturerMapper manufacturerMapper;

    @Transactional
    public ManufacturerDto save(final ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = manufacturerMapper.toManufacturer(manufacturerDto);
        if (manufacturer.getId() != null) {
            manufacturerDao.findById(manufacturerDto.getManufacturerId()).ifPresent(
                    (p) -> manufacturer.setVersion(p.getVersion())
            );
        }
        return manufacturerMapper.toManufacturerDto(manufacturerDao.save(manufacturer));
    }


    @Transactional(readOnly = true)
    public ManufacturerDto findById(Long id) {
        return manufacturerMapper.toManufacturerDto(manufacturerDao.findById(id).orElse(null));
    }


    public List<ManufacturerDto> findAll() {
        return manufacturerDao.findAll().stream().map(manufacturerMapper::toManufacturerDto).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        try {
            manufacturerDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
        }
    }
}
