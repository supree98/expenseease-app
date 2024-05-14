import React, { useState, useEffect } from 'react';
import ListGroup from 'react-bootstrap/ListGroup';
import { FETCH_GROUPS_ENDPOINT } from '../../../public/config/apiEndpoints';
import axios from 'axios';

function Groups() {
    const [groups, setGroups] = useState([]);

    useEffect(() => {
        const fetchGroups = async () => {
            try {
                const response = await axios.get(FETCH_GROUPS_ENDPOINT, {
                    headers: {
                        'Authorization': `Bearer ${localStorage.getItem('TOKEN')}`
                    },
                });

                if (response.status === 200) {
                    setGroups(response.data.data.groups);
                } else {
                    console.error('API call failed');
                }
                // Set the groups state with the response data
            } catch (error) {
                console.error('Error fetching groups:', error);
            }
        };

        // Call the fetchGroups function when the component mounts
        fetchGroups();

        // Cleanup function (optional)
        return () => {
            // Perform cleanup (if needed)
        };
    }, []);
    return (
        <ListGroup>
            {groups.map((group) => (
                <ListGroup.Item key={group.id}>{group.name}</ListGroup.Item>
            ))}
        </ListGroup>
    );
}

export default Groups;