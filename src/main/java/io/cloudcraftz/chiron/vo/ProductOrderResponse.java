/**
 *
 */
package io.cloudcraftz.chiron.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author tuhin
 *
 */
@Getter
@Setter
@ToString
public class ProductOrderResponse {
    //TODO we need to add more information on the response object
    private String status;
    private List<String> TruHrtData;
    private List<String> GypsuppData;
}
