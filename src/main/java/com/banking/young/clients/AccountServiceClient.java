package com.banking.young.clients;

import com.banking.young.web.dto.OpenAccountForm;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class AccountServiceClient {

    private RestTemplate template;

    private static final String url = "http://localhost:8080/accounts/";

    public AccountServiceClient(RestTemplate template) {
        this.template = template;
    }

    public void initiateAccount(OpenAccountForm form) {

        AccountInfoDto info = new AccountInfoDto();
        info.setFirstName(form.getFirstName());
        info.setLastName(form.getLastName());
        info.setDob(form.getDob());
        info.setUsername(form.getUsername());

        System.out.println("post body:");
        System.out.println(info);

        template.postForEntity(url + "initiate", info,null);
    }

    public List<AccountInfoDto> listAllAccounts() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ParameterizedTypeReference<List<AccountInfoDto>> typeRef = new ParameterizedTypeReference<List<AccountInfoDto>>() {
        };

        ResponseEntity<List<AccountInfoDto>> entity = template.exchange(
                url + "all",
                HttpMethod.GET,
                requestEntity,
                typeRef);

        return entity.getBody();
    }


}
