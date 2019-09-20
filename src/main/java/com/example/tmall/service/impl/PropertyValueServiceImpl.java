package com.example.tmall.service.impl;

import com.example.tmall.mapper.PropertyValueMapper;
import com.example.tmall.pojo.Product;
import com.example.tmall.pojo.Property;
import com.example.tmall.pojo.PropertyValue;
import com.example.tmall.pojo.PropertyValueExample;
import com.example.tmall.service.PropertyService;
import com.example.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;
    @Override
    public void init(Product p) {
        List<Property> pts =  propertyService.list(p.getCid());

        for(Property property : pts){
            PropertyValue propertyValue = get(property.getId(), p.getId());
            if(null == propertyValue){
                propertyValue = new PropertyValue();
                propertyValue.setPid(p.getId());
                propertyValue.setPtid(property.getId());
                propertyValueMapper.insert(propertyValue);
            }
        }
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
    }

    @Override
    public PropertyValue get(int ptid, int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPtidEqualTo(ptid).andPidEqualTo(pid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        if(pvs.isEmpty())
            return null;
        return pvs.get(0);
    }

    @Override
    public List<PropertyValue> list(int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> result = propertyValueMapper.selectByExample(example);
        for(PropertyValue pv : result){
            Property property =  propertyService.get(pv.getPtid());
            pv.setProperty(property);
        }
        return result;
    }
}
