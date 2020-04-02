package cn.caraliu.service.impl;

import cn.caraliu.service.MultiDatasouceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenjianhui on 2020/04/02
 */
@Service
public class MultiDatasouceServiceImpl implements MultiDatasouceService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Qualifier("jdbcTemplate0")
    @Autowired
    private JdbcTemplate jdbcTemplate0;
    @Qualifier("jdbcTemplate1")
    @Autowired
    private JdbcTemplate jdbcTemplate1;

    @Transactional(transactionManager = "rttm",rollbackFor = RuntimeException.class,propagation = Propagation.REQUIRED)
    @Override
    public void executeDatasource() {
        jdbcTemplate1.update("INSERT INTO `multi_datasource1`.`black_user` (`app_type`, `user_pk`) VALUES ('1', '3')");
        jdbcTemplate0.update("INSERT INTO `mutil_datasource0`.`black_user` (`app_type`, `user_pk`) VALUES ('1', '3')");

        int i = 10;
        i = i / 0;
    }
}
