package es.cuatrogatos.coinbase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * {
 *     "data": {
 *         "currency": "USD",
 *         "rates": {
 *             "AED": "3.673",
 *             "AFN": "90.89653",
 *             "ALL": "106.120767",
 *             "AMD": "476.451695",
 *             "ANG": "1.800934",
 *             "AOA": "597",
 *             "ARS": "100.0105",
 *             "AUD": "1.346322",
 *             "AWG": "1.801",
 *             "AZN": "1.700805",
 *             "BAM": "1.688943",
 *             "BBD": "2",
 *             "BDT": "85.682032",
 *             "BGN": "1.687247",
 *             "BHD": "0.377043",
 *             "BIF": "1988.661874",
 *             "BMD": "1",
 *             "BND": "1.347645",
 *             "BOB": "6.88995",
 *             "BRL": "5.5446",
 *             "BSD": "1",
 *             "BTN": "74.00754",
 *             "BWP": "11.335987",
 *             "BYN": "2.443999",
 *             "BYR": "24439.989999999998",
 *             "BZD": "2.014214",
 *             "CAD": "1.243056",
 *             "CDF": "2002.655789",
 *             "CHF": "0.912945",
 *             "CLF": "0.029126",
 *             "CLP": "803.67",
 *             "CNY": "6.3901",
 *             "COP": "3867.38258",
 *             "CRC": "639.933977",
 *             "CUC": "1",
 *             "CVE": "95.5",
 *             "CZK": "21.72401",
 *             "DJF": "177.898512",
 *             "DKK": "6.416504",
 *             "DOP": "56.348527",
 *             "DZD": "137.29",
 *             "EGP": "15.7456",
 *             "ERN": "15.00062",
 *             "ETB": "47.495015",
 *             "EUR": "0.862637",
 *             "FJD": "2.0847",
 *             "FKP": "0.735348",
 *             "GBP": "0.735348",
 *             "GEL": "3.16",
 *             "GHS": "6.110516",
 *             "GIP": "0.735348",
 *             "GMD": "52.1",
 *             "GNF": "9593.430258",
 *             "GTQ": "7.735791",
 *             "GYD": "209.27096",
 *             "HKD": "7.790776",
 *             "HNL": "24.096197",
 *             "HRK": "6.4843",
 *             "HTG": "98.825583",
 *             "HUF": "312.263896",
 *             "IDR": "14244.607701",
 *             "ILS": "3.10346",
 *             "INR": "74.030503",
 *             "IQD": "1457.929697",
 *             "ISK": "129.76",
 *             "JMD": "155.232661",
 *             "JOD": "0.7091",
 *             "JPY": "112.8615",
 *             "KES": "111.7",
 *             "KGS": "84.798899",
 *             "KHR": "4067.540057",
 *             "KMF": "424.875023",
 *             "KRW": "1175.33034",
 *             "KWD": "0.301613",
 *             "KYD": "0.832714",
 *             "KZT": "429.13762",
 *             "LAK": "10422.685015",
 *             "LBP": "1510.858325",
 *             "LKR": "201.352094",
 *             "LRD": "147.224974",
 *             "LSL": "15.006476",
 *             "LYD": "4.558384",
 *             "MAD": "9.062365",
 *             "MDL": "17.550109",
 *             "MGA": "3969.061694",
 *             "MKD": "53.139971",
 *             "MMK": "1813.66746",
 *             "MNT": "2851.992224",
 *             "MOP": "8.016442",
 *             "MRO": "356.999828",
 *             "MUR": "43.25",
 *             "MVR": "15.45",
 *             "MWK": "815.340157",
 *             "MXN": "20.2872",
 *             "MYR": "4.1495",
 *             "MZN": "63.83",
 *             "NAD": "14.96",
 *             "NGN": "413.446941",
 *             "NIO": "35.204054",
 *             "NOK": "8.486054",
 *             "NPR": "118.411928",
 *             "NZD": "1.396986",
 *             "OMR": "0.384987",
 *             "PAB": "1",
 *             "PEN": "4.013315",
 *             "PGK": "3.508662",
 *             "PHP": "50.080499",
 *             "PKR": "170.299913",
 *             "PLN": "3.960882",
 *             "PYG": "6885.599404",
 *             "QAR": "3.638366",
 *             "RON": "4.2689",
 *             "RSD": "101.475127",
 *             "RUB": "71.01265",
 *             "RWF": "1018.453639",
 *             "SAR": "3.750448",
 *             "SBD": "8.035419",
 *             "SCR": "13.335806",
 *             "SEK": "8.54551",
 *             "SHP": "0.735348",
 *             "SLL": "10868.350153",
 *             "SOS": "578.060903",
 *             "SRD": "21.492",
 *             "SSP": "130.26",
 *             "STD": "20956.440504",
 *             "SVC": "8.744236",
 *             "SZL": "15.006477",
 *             "THB": "32.791667",
 *             "TJS": "11.26109",
 *             "TMT": "3.51",
 *             "TND": "2.8325",
 *             "TOP": "2.241504",
 *             "TRY": "9.70373",
 *             "TTD": "6.790729",
 *             "TWD": "27.724099",
 *             "TZS": "2300",
 *             "UAH": "26.062555",
 *             "UGX": "3541.383706",
 *             "UYU": "43.626331",
 *             "UZS": "10685.355489",
 *             "VES": "4.4179",
 *             "VND": "22622.031316",
 *             "VUV": "111.224217",
 *             "WST": "2.568092",
 *             "XAF": "565.85276",
 *             "XAG": "0.04096688",
 *             "XAU": "0.0005479",
 *             "XCD": "2.70255",
 *             "XDR": "0.709552",
 *             "XOF": "565.85276",
 *             "XPD": "0.00047676",
 *             "XPF": "102.939973",
 *             "XPT": "0.00094475",
 *             "YER": "250.124874",
 *             "ZAR": "14.866132",
 *             "ZMW": "17.432169",
 *             "JEP": "0.735348",
 *             "GGP": "0.735348",
 *             "IMP": "0.735348",
 *             "GBX": "16.678644713344966",
 *             "CNH": "6.38838",
 *             "TMM": "164.7503455638498",
 *             "ZWL": "322",
 *             "SGD": "1.345639",
 *             "USD": "1.0",
 *             "BTC": "1.4675384529928723e-05",
 *             "BCH": "0.001489979885271549",
 *             "BSV": "0.005420326862666769",
 *             "ETH": "0.00020788572937227863",
 *             "ETH2": "0.00020788572937227863",
 *             "ETC": "0.016362728975938608",
 *             "LTC": "0.00397408894011048",
 *             "ZRX": "0.7374731882404001",
 *             "USDC": "1.0",
 *             "BAT": "0.8192594795490304",
 *             "MANA": "0.3763937861902626",
 *             "KNC": "0.48929663608562685",
 *             "LINK": "0.02846021013596154",
 *             "DNT": "5.40543462397094",
 *             "MKR": "0.0003095961381844592",
 *             "CVC": "2.043535479863001",
 *             "OMG": "0.06136588179703848",
 *             "DAI": "0.9999600015999359",
 *             "ZEC": "0.005477801210594067",
 *             "XRP": "0.7939656388344791",
 *             "REP": "0.037742970371768256",
 *             "XLM": "2.583505351731336",
 *             "EOS": "0.1922522349322311",
 *             "XTZ": "0.15446639583558597",
 *             "ALGO": "0.506701122342986",
 *             "DASH": "0.004374730680642473",
 *             "ATOM": "0.026586905948820207",
 *             "OXT": "1.7700681476236837",
 *             "COMP": "0.002711386467470141",
 *             "ENJ": "0.3193357815743254",
 *             "REPV2": "0.037742970371768256",
 *             "BAND": "0.10477238200010477",
 *             "NMR": "0.02171771959600698",
 *             "CGLD": "0.14997225513280044",
 *             "UMA": "0.06202512017367034",
 *             "LRC": "0.47773743550544623",
 *             "YFI": "2.8156656881289844e-05",
 *             "UNI": "0.03715179480320695",
 *             "BAL": "0.037437878859634725",
 *             "REN": "1.0033109260559847",
 *             "WBTC": "1.4672474423309397e-05",
 *             "NU": "1.0985389432055366",
 *             "YFII": "0.0002404375000751367",
 *             "FIL": "0.015255937420144703",
 *             "AAVE": "0.002954868811211954",
 *             "BNT": "0.215579963998146",
 *             "GRT": "0.823519723297373",
 *             "SNX": "0.0921429690307481",
 *             "STORJ": "0.6113403637475164",
 *             "SUSHI": "0.08097493825660958",
 *             "MATIC": "0.5335325188070212",
 *             "SKL": "2.6606358919781825",
 *             "ADA": "0.44270314540584815",
 *             "ANKR": "7.786645902277593",
 *             "CRV": "0.20760455484393328",
 *             "ICP": "0.018218420645114277",
 *             "NKN": "1.7352073572791948",
 *             "OGN": "0.9124087591240875",
 *             "1INCH": "0.21755683672359405",
 *             "USDT": "0.9993504222255534",
 *             "FORTH": "0.057463008188478666",
 *             "CTSI": "1.201634222542658",
 *             "TRB": "0.015357798306034846",
 *             "POLY": "1.4394702749388226",
 *             "MIR": "0.28276544606249115",
 *             "RLC": "0.20096463022508038",
 *             "DOT": "0.018893591293833133",
 *             "SOL": "0.004041792130630722",
 *             "DOGE": "3.5631569570639585",
 *             "MLN": "0.007419085597700084",
 *             "GTC": "0.0939364050537786",
 *             "AMP": "16.26545217957059",
 *             "SHIB": "17746.22892635315",
 *             "CHZ": "2.0727536532283137",
 *             "KEEP": "1.362583458236817",
 *             "LPT": "0.015546055188495918",
 *             "QNT": "0.0038126465486017116",
 *             "BOND": "0.028905911258852435",
 *             "RLY": "1.6032064128256511",
 *             "CLV": "0.7220216606498195",
 *             "FARM": "0.005954684848304403",
 *             "MASK": "0.0814000814000814",
 *             "FET": "1.1227124733355787",
 *             "PAX": "0.9950248756218907",
 *             "ACH": "10.098714938524074",
 *             "ASM": "5.8491504109028165",
 *             "PLA": "0.7216829646736188",
 *             "RAI": "0.33001666584162503",
 *             "TRIBE": "0.8517524807291001",
 *             "ORN": "0.10735373054213634",
 *             "IOTX": "4.595377050687009",
 *             "UST": "0.9955201592832256",
 *             "QUICK": "0.002313449237140114",
 *             "AXS": "0.0064775229952066336",
 *             "REQ": "4.558092893933178",
 *             "WLUNA": "0.018914318138831095",
 *             "TRU": "1.5645779550966128",
 *             "RAD": "0.08460236886632826",
 *             "COTI": "1.7554638813306416",
 *             "DDX": "0.16835016835016833",
 *             "SUKU": "0.9476427386875148",
 *             "RGT": "0.022535211267605635",
 *             "XYO": "16.740183974621882",
 *             "ZEN": "0.010560777273207308",
 *             "AUCTION": "0.025490695895997964",
 *             "JASMY": "4.5519721419304915",
 *             "WCFG": "0.5221932114882506",
 *             "BTRST": "0.159655144887044",
 *             "AGLD": "0.35149384885764495",
 *             "AVAX": "0.011190063223857216",
 *             "FX": "0.829153020189876",
 *             "TRAC": "0.48428495326650195",
 *             "LCX": "2.452483139178418",
 *             "ARPA": "5.046681806712087",
 *             "BADGER": "0.02762812543168946",
 *             "KRL": "0.327562769215651",
 *             "PERP": "0.05356186395286555",
 *             "RARI": "0.04094165813715455",
 *             "CRO": "2.7008777852802157"
 *         }
 *     }
 * }
 */


public class ExchangeRates {
    @JsonProperty("data")
    private HashMap<String,Object> data;

    private long updatedAt;

    public String getCurrency() {
        return (String) data.get("currency");
    }

    public HashMap<String, String> getRates() {
        return (HashMap<String, String>) data.get("rates");
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
        Logger.getAnonymousLogger().warning("Updated at:"+this.updatedAt);
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
