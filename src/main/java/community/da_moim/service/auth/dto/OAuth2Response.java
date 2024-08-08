package community.da_moim.service.auth.dto;

public interface OAuth2Response {

    String getProvider();

    String getProviderId();

    String getNickname();
}
