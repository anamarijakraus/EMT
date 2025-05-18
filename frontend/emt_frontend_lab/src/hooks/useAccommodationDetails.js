import {useEffect, useState} from "react";
import hostRepository from "../repository/hostRepository.js";
import accommodationRepository from "../repository/accommodationRepository.js";

const useHostDetails = (id) => {
    const [state, setState] = useState({
        "accommodation": null,
        "host": null,
    });

    useEffect(() => {
        accommodationRepository
            .findById(id)
            .then((response) => {
                setState(prevState => ({...prevState, "accommodation": response.data}));
                hostRepository
                    .findById(response.data.hostID)
                    .then((response) => {
                        setState(prevState => ({...prevState, "host": response.data}));
                    })
                    .catch((error) => console.log(error));
            })
            .catch((error) => console.log(error));
    }, [id]);

    return state;
};

export default useHostDetails;