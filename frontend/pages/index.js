import React, { useState } from 'react';
import { Anchor, Box, Heading, Paragraph,Text, TextInput, Button, Form, List } from 'grommet'
import dynamic from 'next/dynamic';


const HomeComponent = function Home() {

  let [searchResults, setSearchResults] = useState([]);

  let [searchTerm, setSearchTerm] = useState('');

  const doSearch = async (evt) => {
    console.log('Starting search ..');
    let encSearchTerm = encodeURI(searchTerm)
    let res = await fetch(`http://localhost:8080/hazards?query=${encSearchTerm}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    })
    let responseObject = await res.json();
    // earchResults.push(...responseObject)
    setSearchResults(responseObject)
  };

  return (
    <Box align="center" margin="large" direction="column">
      <Heading size='large' color="brand">lighthouse hazard search</Heading>
      <Form onSubmit={doSearch}>
        <Box direction="row" margin="medium">
          <TextInput size="xxlarge" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} />
          <Button size="large" margin="small" primary label="search" type="submit" ></Button>
        </Box>
      </Form>
      <Paragraph>
        Find out more at{' '}
        <Anchor href="#">Lighthouse Team @ #SpaceApps2020</Anchor>
      </Paragraph>
      {

        searchResults.length > 0 && searchResults.map((item, idx) => (
          <Box border={2} margin="small" pad="small" width="medium" fill={true}>
            <Paragraph fill={true}> <Text size="xlarge">{ item.text.slice(0,500)  }</Text></Paragraph>
            <Anchor href="#">View All</Anchor>
          </Box>
        ))

      }

      { /*
      <List data={searchResults} children={(item, index, { active }) => 
          <Box margin="medium"><Paragraph>{item.text}</Paragraph></Box>       
      }>
      </List>
      */
      }
    </Box>
  );
}

export default HomeComponent;

// const toRender = dynamic(() => <HomeComponent />, { ssr: false });
// export default () => <toRender />