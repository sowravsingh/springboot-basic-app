package com.spring.serivces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.pojos.CropData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class CropService {

    Logger logger = LoggerFactory.getLogger(CropService.class);
//    Log log


    public CropData getCropData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
       // CropData cropData = new CropData("paddy",12,"pd");
        String jsonObject ="{\"cropId\":12,\"sn\":\"pd\"}";
        logger.info("Inside getCropData method ");
        CropData cropData = objectMapper.readValue(jsonObject,CropData.class);
        return cropData;
    }
}
