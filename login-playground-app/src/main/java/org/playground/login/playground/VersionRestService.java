package org.playground.login.playground;

import org.playground.login.playground.pojo.VersionResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class VersionRestService {


    @RequestMapping()
    @ResponseBody
    public VersionResponse getVersion () {
        return new VersionResponse();
    }
}
