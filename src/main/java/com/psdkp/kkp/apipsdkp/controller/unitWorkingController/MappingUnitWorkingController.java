package com.psdkp.kkp.apipsdkp.controller.unitWorkingController;

import com.psdkp.kkp.apipsdkp.domain.Input;
import com.psdkp.kkp.apipsdkp.domain.unitWorking.MappingUnitWorking;
import com.psdkp.kkp.apipsdkp.service.unitWorking.impl.MappingUnitWorkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/areas/mappingUnit")
public class MappingUnitWorkingController {

    @Autowired
    private MappingUnitWorkingServiceImpl mappingUnitWorkingService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            Pageable pageable
    ) {
        if (id != null) {
            return mappingUnitWorkingService.findById(id);
        } else {
            return mappingUnitWorkingService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveUnitWorking(@RequestBody MappingUnitWorking unitWorking) {
        return mappingUnitWorkingService.save(unitWorking);
    }

    @PutMapping
    public Object editUnitWorking(@RequestBody MappingUnitWorking unitWorking){
        return mappingUnitWorkingService.edit(unitWorking);
    }

    @DeleteMapping(value = "/del")
    public Object removeUnitWorking(@RequestBody Input input){
        return mappingUnitWorkingService.del(input.getId());
    }
}
